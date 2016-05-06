

-------------------------------------------------------------------------------
--  menu
-------------------------------------------------------------------------------
CREATE TABLE AUTH_MENU(
        ID BIGINT NOT NULL,
	TYPE VARCHAR(50),
	CODE VARCHAR(50),
	TITLE VARCHAR(50),
	URL VARCHAR(200),
	PRIORITY INTEGER,
	DESCN VARCHAR(200),
	PARENT_ID BIGINT,
	PERM_ID BIGINT,

	TENANT_ID VARCHAR(64),

	CONSTRAINT PK_AUTH_MENU PRIMARY KEY(ID),
	CONSTRAINT FK_AUTH_MENU_PARENT FOREIGN KEY(PARENT_ID) REFERENCES AUTH_MENU(ID),
	CONSTRAINT FK_AUTH_MENU_PERM FOREIGN KEY(PERM_ID) REFERENCES AUTH_PERM(ID)
);

COMMENT ON TABLE AUTH_MENU IS '菜单';
COMMENT ON COLUMN AUTH_MENU.ID IS '主键';
COMMENT ON COLUMN AUTH_MENU.TYPE IS '分类';
COMMENT ON COLUMN AUTH_MENU.CODE IS '编码';
COMMENT ON COLUMN AUTH_MENU.TITLE IS '标题';
COMMENT ON COLUMN AUTH_MENU.URL IS '链接';
COMMENT ON COLUMN AUTH_MENU.PRIORITY IS '排序';
COMMENT ON COLUMN AUTH_MENU.DESCN IS '备注';
COMMENT ON COLUMN AUTH_MENU.PARENT_ID IS '外键，上级菜单';
COMMENT ON COLUMN AUTH_MENU.PERM_ID IS '外键，权限';
COMMENT ON COLUMN AUTH_MENU.TENANT_ID IS '租户';
