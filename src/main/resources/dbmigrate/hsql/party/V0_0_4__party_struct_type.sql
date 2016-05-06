
CREATE TABLE PARTY_STRUCT_TYPE(
        ID BIGINT NOT NULL,
        NAME VARCHAR(50),
	REF VARCHAR(50),
	DIM_ID BIGINT,
	SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_PARTY_STRUCT_TYPE PRIMARY KEY(ID),
        CONSTRAINT FK_PARTY_STRUCT_TYPE_DIM FOREIGN KEY(DIM_ID) REFERENCES PARTY_DIM(ID)
);

COMMENT ON TABLE PARTY_STRUCT_TYPE IS '组织关系类型';
COMMENT ON COLUMN PARTY_STRUCT_TYPE.ID IS '主键';
COMMENT ON COLUMN PARTY_STRUCT_TYPE.NAME IS '名称';
COMMENT ON COLUMN PARTY_STRUCT_TYPE.REF IS '外部引用';
COMMENT ON COLUMN PARTY_STRUCT_TYPE.SCOPE_ID IS '租户';
