prompt PL/SQL Developer import file
prompt Created on 2016年11月17日 by jiuhua
set feedback off
set define off
prompt Creating CONTRACT_C...
create table CONTRACT_C
(
  contract_id     VARCHAR2(40) not null,
  offeror         VARCHAR2(200),
  contract_no     VARCHAR2(50),
  signing_date    TIMESTAMP(6),
  input_by        VARCHAR2(30),
  check_by        VARCHAR2(30),
  inspector       VARCHAR2(30),
  total_amount    NUMBER(10,2),
  import_num      INTEGER,
  crequest        VARCHAR2(2000),
  custom_name     VARCHAR2(200),
  delivery_period TIMESTAMP(6),
  ship_time       TIMESTAMP(6),
  trade_terms     VARCHAR2(30),
  remark          VARCHAR2(600),
  print_style     CHAR(1),
  old_state       INTEGER,
  state           INTEGER,
  out_state       INTEGER,
  create_by       VARCHAR2(40),
  create_dept     VARCHAR2(40),
  create_time     TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CONTRACT_C
  add constraint PK_CONTRACT_C primary key (CONTRACT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CONTRACT_HIS_C...
create table CONTRACT_HIS_C
(
  contract_id     VARCHAR2(40) not null,
  offeror         VARCHAR2(200),
  contract_no     VARCHAR2(50),
  signing_date    TIMESTAMP(6),
  input_by        VARCHAR2(30),
  check_by        VARCHAR2(30),
  inspector       VARCHAR2(30),
  total_amount    NUMBER(10,2),
  import_num      INTEGER,
  crequest        VARCHAR2(2000),
  custom_name     VARCHAR2(200),
  delivery_period TIMESTAMP(6),
  ship_time       TIMESTAMP(6),
  trade_terms     VARCHAR2(30),
  remark          VARCHAR2(600),
  print_style     CHAR(1),
  old_state       INTEGER,
  state           INTEGER,
  out_state       INTEGER,
  create_by       VARCHAR2(40),
  create_dept     VARCHAR2(40),
  create_time     TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CONTRACT_HIS_C
  add constraint PK_CONTRACT_HIS_C primary key (CONTRACT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CONTRACT_PRODUCT_C...
create table CONTRACT_PRODUCT_C
(
  contract_product_id VARCHAR2(40) not null,
  factory_id          VARCHAR2(40),
  contract_id         VARCHAR2(40),
  factory_na          VARCHAR2(50),
  product_no          VARCHAR2(50),
  product_image       VARCHAR2(200),
  product_desc        VARCHAR2(600),
  cnumber             INTEGER,
  out_number          INTEGER,
  loading_rate        VARCHAR2(10),
  box_num             INTEGER,
  packing_unit        VARCHAR2(10),
  price               NUMBER(10,2),
  amount              NUMBER(10,2),
  finished            INTEGER,
  exts                VARCHAR2(50),
  order_no            INTEGER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CONTRACT_PRODUCT_C
  add constraint PK_CONTRACT_PRODUCT_C primary key (CONTRACT_PRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CONTRACT_PRODUCT_HIS_C...
create table CONTRACT_PRODUCT_HIS_C
(
  contract_product_id VARCHAR2(40) not null,
  factory_id          VARCHAR2(40),
  contract_id         VARCHAR2(40),
  factory_na          VARCHAR2(50),
  product_no          VARCHAR2(50),
  product_image       VARCHAR2(200),
  product_desc        VARCHAR2(600),
  cnumber             INTEGER,
  out_number          INTEGER,
  loading_rate        VARCHAR2(10),
  box_num             INTEGER,
  packing_unit        VARCHAR2(10),
  price               NUMBER(10,2),
  amount              NUMBER(10,2),
  finished            INTEGER,
  exts                VARCHAR2(50),
  order_no            INTEGER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CONTRACT_PRODUCT_HIS_C
  add constraint PK_CONTRACT_PRODUCT_HIS_C primary key (CONTRACT_PRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating EXPORT_C...
create table EXPORT_C
(
  export_id         VARCHAR2(40) not null,
  input_date        TIMESTAMP(6),
  contract_ids      VARCHAR2(500),
  customer_contract VARCHAR2(200),
  lcno              VARCHAR2(10),
  consignee         VARCHAR2(100),
  marks             VARCHAR2(1000),
  remark            VARCHAR2(100),
  shipment_port     VARCHAR2(100),
  destination_port  VARCHAR2(100),
  transport_mode    VARCHAR2(10),
  price_condition   VARCHAR2(10),
  state             INTEGER,
  gross_weight      NUMBER(5,2),
  net_weight        NUMBER(5,2),
  measurement       NUMBER(8,2),
  create_by         VARCHAR2(40),
  create_dept       VARCHAR2(40),
  create_time       TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table EXPORT_C
  add constraint PK_EXPORT_C primary key (EXPORT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating EXPORT_PRODUCT_C...
create table EXPORT_PRODUCT_C
(
  export_product_id VARCHAR2(40) not null,
  export_id         VARCHAR2(40),
  factory_id        VARCHAR2(40),
  factory_na        VARCHAR2(50),
  product_no        VARCHAR2(50),
  packing_unit      VARCHAR2(10),
  cnumber           INTEGER,
  box_num           INTEGER,
  gross_weight      NUMBER(8,2),
  net_weight        NUMBER(8,2),
  size_length       NUMBER(8,2),
  size_width        NUMBER(8,2),
  size_height       NUMBER(8,2),
  ex_price          NUMBER(8,2),
  price             NUMBER(10,2),
  tax               NUMBER(8,2),
  order_no          INTEGER,
  product_na        VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table EXPORT_PRODUCT_C
  add constraint PK_EXPORT_PRODUCT_C primary key (EXPORT_PRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating EXT_CPRODUCT_C...
create table EXT_CPRODUCT_C
(
  ext_cproduct_id     VARCHAR2(40) not null,
  factory_id          VARCHAR2(40),
  contract_product_id VARCHAR2(40),
  factory_na          VARCHAR2(50),
  ctype               INTEGER,
  product_no          VARCHAR2(50),
  product_image       VARCHAR2(200),
  product_desc        VARCHAR2(600),
  cnumber             INTEGER,
  packing_unit        VARCHAR2(10),
  price               NUMBER(10,2),
  amount              NUMBER(10,2),
  product_request     VARCHAR2(2000),
  order_no            INTEGER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table EXT_CPRODUCT_C
  add constraint PK_EXT_CPRODUCT_C primary key (EXT_CPRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating EXT_CPRODUCT_HIS_C...
create table EXT_CPRODUCT_HIS_C
(
  ext_cproduct_id     VARCHAR2(40) not null,
  factory_id          VARCHAR2(40),
  contract_product_id VARCHAR2(40),
  factory_na          VARCHAR2(50),
  ctype               INTEGER,
  product_no          VARCHAR2(50),
  product_image       VARCHAR2(200),
  product_desc        VARCHAR2(600),
  cnumber             INTEGER,
  packing_unit        VARCHAR2(10),
  price               NUMBER(10,2),
  amount              NUMBER(10,2),
  product_request     VARCHAR2(2000),
  order_no            INTEGER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table EXT_CPRODUCT_HIS_C
  add constraint PK_EXT_CPRODUCT_HIS_C primary key (EXT_CPRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating EXT_EPRODUCT_C...
create table EXT_EPRODUCT_C
(
  ext_eproduct_id   VARCHAR2(40) not null,
  export_product_id VARCHAR2(40),
  factory_id        VARCHAR2(40),
  factory_na        VARCHAR2(50),
  ctype             INTEGER,
  product_no        VARCHAR2(50),
  prodcut_image     VARCHAR2(200),
  product_desc      VARCHAR2(600),
  cnumber           INTEGER,
  pracking_nuit     VARCHAR2(10),
  price             NUMBER(10,2),
  amount            NUMBER(10,2),
  product_request   VARCHAR2(2000),
  order_no          INTEGER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table EXT_EPRODUCT_C
  add constraint PK_EXT_EPRODUCT_C primary key (EXT_EPRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating FACTORY_C...
create table FACTORY_C
(
  factory_id  VARCHAR2(40) not null,
  full_name   VARCHAR2(200),
  factory_na  VARCHAR2(50),
  contacts    VARCHAR2(30),
  phone       VARCHAR2(20),
  mobile      VARCHAR2(20),
  fax         VARCHAR2(20),
  note        VARCHAR2(20),
  inspector   VARCHAR2(30),
  state       CHAR(1),
  order_no    INTEGER,
  create_by   VARCHAR2(40),
  create_dept VARCHAR2(40),
  create_time TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table FACTORY_C
  add constraint PK_FACTORY_C primary key (FACTORY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating FINANCE_C...
create table FINANCE_C
(
  finance_id  VARCHAR2(40) not null,
  input_date  TIMESTAMP(6),
  input_by    VARCHAR2(30),
  create_by   VARCHAR2(40),
  create_dept VARCHAR2(40),
  create_time TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
alter table FINANCE_C
  add constraint PK_FINANCE_C primary key (FINANCE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating INVOICE_C...
create table INVOICE_C
(
  invoice_id  VARCHAR2(40),
  sc_no       VARCHAR2(100),
  bl_no       VARCHAR2(100),
  trade_terms VARCHAR2(100),
  create_by   VARCHAR2(40),
  create_dept VARCHAR2(40),
  create_time TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

prompt Creating PACKING_LIST_C...
create table PACKING_LIST_C
(
  packing_list_id VARCHAR2(40) not null,
  seller          VARCHAR2(200),
  bayer           VARCHAR2(200),
  invoice_no      VARCHAR2(30),
  invoice_data    TIMESTAMP(6),
  marks           VARCHAR2(600),
  desriptions     VARCHAR2(200),
  export_ids      VARCHAR2(200),
  export_nos      VARCHAR2(200),
  create_by       VARCHAR2(40),
  create_dept     VARCHAR2(40),
  create_time     TIMESTAMP(6),
  state           INTEGER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PACKING_LIST_C
  add constraint PK_PACKING_LIST_C primary key (PACKING_LIST_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SHIPPING_ORDER_C...
create table SHIPPING_ORDER_C
(
  shipping_order    VARCHAR2(40) not null,
  order_type        CHAR(6),
  shipper           VARCHAR2(200),
  consignee         VARCHAR2(200),
  notify_party      VARCHAR2(200),
  lc_no             VARCHAR2(30),
  port_of_loading   VARCHAR2(30),
  port_of_trans     VARCHAR2(30),
  port_of_discharge VARCHAR2(30),
  loading_date      TIMESTAMP(6),
  limt_date         TIMESTAMP(6),
  is_batch          CHAR(1),
  is_trans          CHAR(1),
  copy_num          VARCHAR2(20),
  cnote             VARCHAR2(200),
  special_condition CHAR(10),
  freight           VARCHAR2(200),
  check_by          VARCHAR2(30),
  create_by         VARCHAR2(40),
  create_dept       VARCHAR2(40),
  create_time       TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
alter table SHIPPING_ORDER_C
  add constraint PK_SHIPPING_ORDER_C primary key (SHIPPING_ORDER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Disabling triggers for CONTRACT_C...
alter table CONTRACT_C disable all triggers;
prompt Disabling triggers for CONTRACT_HIS_C...
alter table CONTRACT_HIS_C disable all triggers;
prompt Disabling triggers for CONTRACT_PRODUCT_C...
alter table CONTRACT_PRODUCT_C disable all triggers;
prompt Disabling triggers for CONTRACT_PRODUCT_HIS_C...
alter table CONTRACT_PRODUCT_HIS_C disable all triggers;
prompt Disabling triggers for EXPORT_C...
alter table EXPORT_C disable all triggers;
prompt Disabling triggers for EXPORT_PRODUCT_C...
alter table EXPORT_PRODUCT_C disable all triggers;
prompt Disabling triggers for EXT_CPRODUCT_C...
alter table EXT_CPRODUCT_C disable all triggers;
prompt Disabling triggers for EXT_CPRODUCT_HIS_C...
alter table EXT_CPRODUCT_HIS_C disable all triggers;
prompt Disabling triggers for EXT_EPRODUCT_C...
alter table EXT_EPRODUCT_C disable all triggers;
prompt Disabling triggers for FACTORY_C...
alter table FACTORY_C disable all triggers;
prompt Disabling triggers for FINANCE_C...
alter table FINANCE_C disable all triggers;
prompt Disabling triggers for INVOICE_C...
alter table INVOICE_C disable all triggers;
prompt Disabling triggers for PACKING_LIST_C...
alter table PACKING_LIST_C disable all triggers;
prompt Disabling triggers for SHIPPING_ORDER_C...
alter table SHIPPING_ORDER_C disable all triggers;
prompt Loading CONTRACT_C...
insert into CONTRACT_C (contract_id, offeror, contract_no, signing_date, input_by, check_by, inspector, total_amount, import_num, crequest, custom_name, delivery_period, ship_time, trade_terms, remark, print_style, old_state, state, out_state, create_by, create_dept, create_time)
values ('7d81586b-5a7c-4e41-80c8-7eaa660b2972', '杰信商贸有限公司', '1', to_timestamp('25-10-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '15', '1', '1', 45, 3, '1', '1', to_timestamp('02-10-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('25-10-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', '1', '2', 0, 1, 0, '升水', '升水', to_timestamp('25-10-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 1 records loaded
prompt Loading CONTRACT_HIS_C...
insert into CONTRACT_HIS_C (contract_id, offeror, contract_no, signing_date, input_by, check_by, inspector, total_amount, import_num, crequest, custom_name, delivery_period, ship_time, trade_terms, remark, print_style, old_state, state, out_state, create_by, create_dept, create_time)
values ('06648105-bb9f-4fab-a146-53dfa24860ad', '杰信商贸有限公司', '12', to_timestamp('06-11-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '12', '12', '12', null, 3, '12', '12', to_timestamp('08-11-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('12-11-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '12', '12', '2', 0, 1, 0, null, null, null);
commit;
prompt 1 records loaded
prompt Loading CONTRACT_PRODUCT_C...
insert into CONTRACT_PRODUCT_C (contract_product_id, factory_id, contract_id, factory_na, product_no, product_image, product_desc, cnumber, out_number, loading_rate, box_num, packing_unit, price, amount, finished, exts, order_no)
values ('f016a5c5-c56f-4a62-99b7-b5f398147f5a', '67398e4c-bda2-40dc-b273-b0568f552f7b', '7d81586b-5a7c-4e41-80c8-7eaa660b2972', '11', '1', '1', '1', 1, null, '1', 1, '1', 1, 1, null, null, null);
insert into CONTRACT_PRODUCT_C (contract_product_id, factory_id, contract_id, factory_na, product_no, product_image, product_desc, cnumber, out_number, loading_rate, box_num, packing_unit, price, amount, finished, exts, order_no)
values ('bb5881b7-99c1-4c48-8591-b197d671c27b', '4bb9b1e6-92d1-412c-bbfc-2796f67264af', '7d81586b-5a7c-4e41-80c8-7eaa660b2972', '12', '12', '13', '12', 12, null, '12', 12, '12', 12, 144, null, null, null);
insert into CONTRACT_PRODUCT_C (contract_product_id, factory_id, contract_id, factory_na, product_no, product_image, product_desc, cnumber, out_number, loading_rate, box_num, packing_unit, price, amount, finished, exts, order_no)
values ('20160388-63ab-4750-8789-4e561654198c', '4bb9b1e6-92d1-412c-bbfc-2796f67264af', '7d81586b-5a7c-4e41-80c8-7eaa660b2972', '12', '13', '123', '132', 123, null, '32', 213, '123', 123, 15129, null, null, null);
commit;
prompt 3 records loaded
prompt Loading CONTRACT_PRODUCT_HIS_C...
insert into CONTRACT_PRODUCT_HIS_C (contract_product_id, factory_id, contract_id, factory_na, product_no, product_image, product_desc, cnumber, out_number, loading_rate, box_num, packing_unit, price, amount, finished, exts, order_no)
values ('11', '11', '11', '11', '11', '11', '11', 11, null, null, null, null, null, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading EXPORT_C...
insert into EXPORT_C (export_id, input_date, contract_ids, customer_contract, lcno, consignee, marks, remark, shipment_port, destination_port, transport_mode, price_condition, state, gross_weight, net_weight, measurement, create_by, create_dept, create_time)
values ('dd29f180-f9dd-42fe-b751-b5780f74611d', null, '7d81586b-5a7c-4e41-80c8-7eaa660b2972', '1', null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, null, null);
insert into EXPORT_C (export_id, input_date, contract_ids, customer_contract, lcno, consignee, marks, remark, shipment_port, destination_port, transport_mode, price_condition, state, gross_weight, net_weight, measurement, create_by, create_dept, create_time)
values ('efa01c31-07a5-491d-a3f4-897cdb25eb60', null, '7d81586b-5a7c-4e41-80c8-7eaa660b2972', '1', null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, null, null);
commit;
prompt 2 records loaded
prompt Loading EXPORT_PRODUCT_C...
insert into EXPORT_PRODUCT_C (export_product_id, export_id, factory_id, factory_na, product_no, packing_unit, cnumber, box_num, gross_weight, net_weight, size_length, size_width, size_height, ex_price, price, tax, order_no, product_na)
values ('bea38490-e113-4274-ac95-7e52066383e1', 'dd29f180-f9dd-42fe-b751-b5780f74611d', '4bb9b1e6-92d1-412c-bbfc-2796f67264af', null, '13', null, 123, 213, null, null, null, null, null, null, 123, null, null, null);
insert into EXPORT_PRODUCT_C (export_product_id, export_id, factory_id, factory_na, product_no, packing_unit, cnumber, box_num, gross_weight, net_weight, size_length, size_width, size_height, ex_price, price, tax, order_no, product_na)
values ('73f2b252-bb29-47cf-94b0-bc2e1507b547', 'dd29f180-f9dd-42fe-b751-b5780f74611d', '4bb9b1e6-92d1-412c-bbfc-2796f67264af', null, '12', null, 12, 12, null, null, null, null, null, null, 12, null, null, null);
insert into EXPORT_PRODUCT_C (export_product_id, export_id, factory_id, factory_na, product_no, packing_unit, cnumber, box_num, gross_weight, net_weight, size_length, size_width, size_height, ex_price, price, tax, order_no, product_na)
values ('4d88d17a-4f5c-4dee-94d2-2ed306c0d15f', 'dd29f180-f9dd-42fe-b751-b5780f74611d', '67398e4c-bda2-40dc-b273-b0568f552f7b', null, '1', null, 1, 1, null, null, null, null, null, null, 1, null, null, null);
insert into EXPORT_PRODUCT_C (export_product_id, export_id, factory_id, factory_na, product_no, packing_unit, cnumber, box_num, gross_weight, net_weight, size_length, size_width, size_height, ex_price, price, tax, order_no, product_na)
values ('23007dd4-8b4d-4e0b-b6cc-263f93649078', 'efa01c31-07a5-491d-a3f4-897cdb25eb60', '4bb9b1e6-92d1-412c-bbfc-2796f67264af', null, '13', null, 123, 213, null, null, null, null, null, null, 123, null, null, null);
insert into EXPORT_PRODUCT_C (export_product_id, export_id, factory_id, factory_na, product_no, packing_unit, cnumber, box_num, gross_weight, net_weight, size_length, size_width, size_height, ex_price, price, tax, order_no, product_na)
values ('ff5cbe65-9a0e-4835-8c6f-850cdd187866', 'efa01c31-07a5-491d-a3f4-897cdb25eb60', '67398e4c-bda2-40dc-b273-b0568f552f7b', null, '1', null, 1, 1, null, null, null, null, null, null, 1, null, null, null);
insert into EXPORT_PRODUCT_C (export_product_id, export_id, factory_id, factory_na, product_no, packing_unit, cnumber, box_num, gross_weight, net_weight, size_length, size_width, size_height, ex_price, price, tax, order_no, product_na)
values ('02e52fb4-4710-40b3-8553-8b4f0a420760', 'efa01c31-07a5-491d-a3f4-897cdb25eb60', '4bb9b1e6-92d1-412c-bbfc-2796f67264af', null, '12', null, 12, 12, null, null, null, null, null, null, 12, null, null, null);
commit;
prompt 6 records loaded
prompt Loading EXT_CPRODUCT_C...
prompt Table is empty
prompt Loading EXT_CPRODUCT_HIS_C...
insert into EXT_CPRODUCT_HIS_C (ext_cproduct_id, factory_id, contract_product_id, factory_na, ctype, product_no, product_image, product_desc, cnumber, packing_unit, price, amount, product_request, order_no)
values ('11', '11', '11', '11', 1, '11', '11', null, null, null, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading EXT_EPRODUCT_C...
insert into EXT_EPRODUCT_C (ext_eproduct_id, export_product_id, factory_id, factory_na, ctype, product_no, prodcut_image, product_desc, cnumber, pracking_nuit, price, amount, product_request, order_no)
values ('0361c4bc-1a0b-44c3-ac78-d64a4eadad70', '4d88d17a-4f5c-4dee-94d2-2ed306c0d15f', null, null, 0, null, null, null, 0, null, null, 0, null, 0);
commit;
prompt 1 records loaded
prompt Loading FACTORY_C...
insert into FACTORY_C (factory_id, full_name, factory_na, contacts, phone, mobile, fax, note, inspector, state, order_no, create_by, create_dept, create_time)
values ('67398e4c-bda2-40dc-b273-b0568f552f7b', '11', '11', '2', '11', '11', '11', '1', '1', '1', 1, null, null, null);
insert into FACTORY_C (factory_id, full_name, factory_na, contacts, phone, mobile, fax, note, inspector, state, order_no, create_by, create_dept, create_time)
values ('4bb9b1e6-92d1-412c-bbfc-2796f67264af', '12', '12', '12', '12', '12', '12', '12', '12', '1', 12, null, null, null);
insert into FACTORY_C (factory_id, full_name, factory_na, contacts, phone, mobile, fax, note, inspector, state, order_no, create_by, create_dept, create_time)
values ('55d994f7-d1ed-44d4-a86b-84bbe438c722', '123', '123', '13', '123', '123', '123', '123', '123', '1', 123, null, null, null);
insert into FACTORY_C (factory_id, full_name, factory_na, contacts, phone, mobile, fax, note, inspector, state, order_no, create_by, create_dept, create_time)
values ('1a8cacdc-6f96-4d8b-be9b-e1eb60a727be', '13', '13', '13', '13', '13', '13', '13', '13', '1', 13, null, null, null);
commit;
prompt 4 records loaded
prompt Loading FINANCE_C...
prompt Table is empty
prompt Loading INVOICE_C...
prompt Table is empty
prompt Loading PACKING_LIST_C...
prompt Table is empty
prompt Loading SHIPPING_ORDER_C...
prompt Table is empty
prompt Enabling triggers for CONTRACT_C...
alter table CONTRACT_C enable all triggers;
prompt Enabling triggers for CONTRACT_HIS_C...
alter table CONTRACT_HIS_C enable all triggers;
prompt Enabling triggers for CONTRACT_PRODUCT_C...
alter table CONTRACT_PRODUCT_C enable all triggers;
prompt Enabling triggers for CONTRACT_PRODUCT_HIS_C...
alter table CONTRACT_PRODUCT_HIS_C enable all triggers;
prompt Enabling triggers for EXPORT_C...
alter table EXPORT_C enable all triggers;
prompt Enabling triggers for EXPORT_PRODUCT_C...
alter table EXPORT_PRODUCT_C enable all triggers;
prompt Enabling triggers for EXT_CPRODUCT_C...
alter table EXT_CPRODUCT_C enable all triggers;
prompt Enabling triggers for EXT_CPRODUCT_HIS_C...
alter table EXT_CPRODUCT_HIS_C enable all triggers;
prompt Enabling triggers for EXT_EPRODUCT_C...
alter table EXT_EPRODUCT_C enable all triggers;
prompt Enabling triggers for FACTORY_C...
alter table FACTORY_C enable all triggers;
prompt Enabling triggers for FINANCE_C...
alter table FINANCE_C enable all triggers;
prompt Enabling triggers for INVOICE_C...
alter table INVOICE_C enable all triggers;
prompt Enabling triggers for PACKING_LIST_C...
alter table PACKING_LIST_C enable all triggers;
prompt Enabling triggers for SHIPPING_ORDER_C...
alter table SHIPPING_ORDER_C enable all triggers;
set feedback on
set define on
prompt Done.
