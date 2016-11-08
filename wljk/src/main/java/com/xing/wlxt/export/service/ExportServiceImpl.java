package com.xing.wlxt.export.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xing.wlxt.contract.dao.ContractMapper;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.contractExtProduct.view.ContractExtProductVO;
import com.xing.wlxt.contractProduct.view.ContractProductVO;
import com.xing.wlxt.export.dao.ExportMapper;
import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.export.search.ExportSearch;
import com.xing.wlxt.export.view.ExportVO;
import com.xing.wlxt.exportExtProduct.dao.ExportExtProductMapper;
import com.xing.wlxt.exportExtProduct.entity.ExportExtProduct;
import com.xing.wlxt.exportProduct.dao.ExportProductMapper;
import com.xing.wlxt.exportProduct.entity.ExportProduct;
import com.xing.wlxt.utils.UtilFuns;
import com.xing.wlxt.utils.WLXTUtils;

@Service
@WebService
public class ExportServiceImpl implements ExportService{
	
	@Resource
	ContractMapper contractMapper;
	
	@Resource
	ExportMapper exportMapper;
	
	@Resource
	ExportExtProductMapper exportExtProductMapper;
	
	@Resource
	ExportProductMapper exportProductMapper;
	
	public static ExportMapper mapper;
	/**定义日志对象*/
	Logger logger=Logger.getLogger(ExportServiceImpl.class);
	
	//利用set方法，在cxf中注入mapper，这样cxf的WebService才可以查询我们系统的数据
	@WebMethod(exclude=true)
	public void setExportMapper(ExportMapper exportMapper){
		this.mapper=exportMapper;
	}
	
	@WebMethod(exclude=true)
	public List<Contract> getContractList() {
		ContractSearch search=new ContractSearch();  //封装查询条件
		search.setState(1);
		List<Contract> dataList=contractMapper.find(search);//获取已上报的购销合同集合
		return dataList;
	}

	@WebMethod(exclude=true)
	public List<Export> find(ExportSearch search) {
		List<Export> dataList=exportMapper.find(search);
		return dataList;
	}
	@WebMethod(exclude=true)
	public Export get(String id) {
		Export export=exportMapper.get(id);
		return export;
	}
	
	public Export getByWS(String id){
		Export export=mapper.get(id);
		return export;
	}

	@WebMethod(exclude=true)
	public void update(Export export) {
		exportMapper.update(export);
	}

	@WebMethod(exclude=true)
	public void delete(String[] ids) {
		
		exportExtProductMapper.deleteByExportId(ids);  //根据报运id来删除报运下的附件信息
		
		exportProductMapper.deleteByExportId(ids);   //根据报运id来删除报运下的货物信息
 		exportMapper.delete(ids);    //删除报运单信息
	}

	@WebMethod(exclude=true)
	public void submit(String [] ids) {
		for(String id:ids){
			Export export=new Export();
			export.setId(id);
			export.setState(1);
			exportMapper.update(export);
		}
		
	}

	@WebMethod(exclude=true)
	public void cancel(String [] ids) {
		for(String id:ids){
			Export export=new Export();
			export.setId(id);
			export.setState(0);
			exportMapper.update(export);
		}
	}

	@WebMethod(exclude=true)
	public ExportVO view(String id) {
		ExportVO obj=exportMapper.view(id);
		return obj;
	}

	@WebMethod(exclude=true)
	public void insert(String[] contractIds) {
		/*
		 * 步骤：
		 * 1、根据合同id获得合同对象，获取合同号
		 * 2、将合同下的货物信息搬家到报运下的货物表中
		 * 3、将合同下的附件信息搬家到报运下的附件表中
		 */
		
		//拼接合同号，报运号
		
		String contractNos="";
		for(int i=0;i<contractIds.length;i++){
			ContractVO contractVO=contractMapper.view(contractIds[i]);
			contractNos+=contractVO.getContractNO()+",";
		}
		contractNos=UtilFuns.delLastChar(contractNos);         //使用工具类删除最后一个字符
		
		Export  export=new Export();                           //创建报运对象
		export.setId(WLXTUtils.createPrimary());               //设置报运单主键
		
		export.setContractIds(UtilFuns.joinStr(contractIds,","));   //设置合同编号集合
		export.setCustomerContract(contractNos);                    //设置合同或确认书号
		
		export.setState(0);                                    //设置报运状态  ，  0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
		exportMapper.insert(export);                           //新增报运信息
		
		//处理货物信息
		for(int i=0;i<contractIds.length;i++){
			ContractVO contractVO=contractMapper.view(contractIds[i]);   //获取购销合同信息
			
			for(ContractProductVO cp:contractVO.getContractProduct()){ //遍历购销合同中的货物信息
				ExportProduct ep=new ExportProduct();   //创建信息报运货物对象
				ep.setId(WLXTUtils.createPrimary());
				
				//数据搬家，将合同下的对应的货物新写到报运下的货物信息中
				if(cp.getFactoryID()!=null){
					ep.setFactoryID(cp.getFactoryID().getId());				//生产厂家id
					ep.setFactoryName(cp.getFactoryID().getFactoryNA());    //生产厂家名称
				}
				ep.setProductNO(cp.getProductNo());                     //货号
				ep.setCnumber(cp.getcNumber());                         //数量
				ep.setBoxNum(cp.getBoxNum());							//箱数
				ep.setPrice(cp.getPrice());                             //商品价格
				ep.setExportID(export.getId());                         //设置外键关联   即在报运的货物信息中设置报运id
 				 
				exportProductMapper.insert(ep);     //新增报运下的货物信息
				
				//处理了附件信息
				for(ContractExtProductVO extProduct:cp.getContractExtProduct()){
					ExportExtProduct extep=new ExportExtProduct();
					
					try {
						BeanUtils.copyProperties(extProduct, extep);
					} catch (IllegalAccessException | InvocationTargetException e) {
						logger.error("购销合同报运时  附件拷贝时错误");
						e.printStackTrace();
					}
					
					extep.setId(WLXTUtils.createPrimary());
					extep.setExportProductID(ep.getId());     //绑定外键   即在附件信息中设置货物id
					
					if(extProduct.getFactoryID()!=null){
						extep.setFactoryID(extProduct.getFactoryID().getId());
						extep.setFactoryNA(extProduct.getFactoryID().getFactoryNA());
					}
					
					exportExtProductMapper.insert(extep);         //插入报运单下所属货物的附件信息
				}
			}
			
		}
		
	}

}
