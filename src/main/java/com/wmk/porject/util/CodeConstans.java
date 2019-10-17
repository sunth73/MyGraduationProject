package com.wmk.porject.util;

/**
 * 数据字典
 *
 * @author 
 * @date 
 */
public interface CodeConstans {

    public static final String JSON = "application/json";
    public static final String JSON_UTF_8 = "application/json; charset=UTF-8";
    public static final int EMPLOYEE_NUMBER_LENGTH = 64;
    public static final int SHIFT_NAME_LENGTH = 32;
    public static final int VISITOR_NAME_LENGTH = 32;
    public static final int IDENTITY_NUMBER_LENGTH = 32;
    public static final int PHONE_NUMBER_LENGTH = 32;
    public static final int COMPANY_NAME_LENGTH = 64;
    public static final int VISIT_PASSWORD_LENGTH = 256;
    public static final int VISIT_REASON_LENGTH = 128;

    public static final String USER_ID = "userId";

    public static final String USER_ID_NOT_EMPTY = "userId不能为空!";

    // redis缓存用户权限key前缀
    public static final String REDIS_KEY_SYSPRI_USER_PRIVILEGE_PREFIX = "syspri_userPrivilege";

    // 菜单模块默认标签名称
    public static final String MENU_MODULE_DEFAULT_LABLE_NAME = "module";

    // 用户默认头像
    public static final String DEFAULT_USER_PHOTO = "1";

    public static final String USER_DEFAULT_PASSWORD = "1234abcd";

    // 默认分隔符
    public static final String DEFAULT_SPLIT_STRING = "$$*$$";
    public static final String DEFAULT_SPLIT_STRING_SIGN = "\\$\\$\\*\\$\\$";

    // 24小时字符形式
    public static final String TWENTY_FOUR_HOUR = "24:00:00";

    // 访客短信审核链接key前缀
    public static final String REDIS_KEY_VISIT_SMS_AUDIT_URL_PREFIX = "visit_smsAuditUrl";

    // 访客短信二维码链接密文前缀key
    public static final String REDIS_KEY_VISIT_SMS_QR_CODE_URL_CIPHERTEXT_PREFIX = "visit_smsOrCodeUrlCiphertext";

    // 访客短信二维码链接密文前缀key
    public static final String REDIS_KEY_VISIT_OPEN_DOOR_PWD = "visit_openDoorPwd";

    // kafka主题(访问开门密码主题)
    public static final String KAFKA_TOPIC_VISIT_OPEN_DOOR_PWD = "topic-visit-open-door-pwd";

    // kafka消费者组(微信公众号)
    public static final String KAFKA_CONSUMER_GROUP_WEBCHAT = "group-WeChat";

    // kafka消费者组(访客机)
    public static final String KAFKA_CONSUMER_GROUP_QL_FK = "group-QLFK";

    // 访客开门密码因子
    public static final String VISTI_OPEN_DOOR_PWD_DIGIT_DIVISOR = "100000";

    // 访客开门密码因子增量最大位数
    public static final int VISTI_OPEN_DOOR_PWD_DIGIT_DIVISOR_INC_NUM = 5;

    // 访客开门密码生成尝试最大次数
    public static final int VISTI_OPEN_DOOR_PWD_PRO_ATTEMPTS = 10000;

    // 访客开门密码插入尝试最大次数
    public static final int VISTI_OPEN_DOOR_PWD_INSERT_ATTEMPTS = 10;

    // kafka主题(发送短信主题)
    public static final String KAFKA_TOPIC_SEND_SMS = "topic-send-sms";

    // kafka消费者组(发送短信)
    public static final String KAFKA_CONSUMER_GROUP_SEND_SMS = "group-send-sms";

    // 短信发送失败尝试最大次数
    public static final int SMS_SEND_FAIL_TRY_MAX_NUM = 3;

    // 短信发送间隔时间
    public static final int SMS_SEND_INTERVAL_SECOND = 2;

    // 访问短信模版id(访问申请,发给被访者)
    public static final String SMS_TEMP_ID_VISIT_APPLY = "10980";

    // 访问短信模版id(访问同意,发给访客)
    public static final String SMS_TEMP_ID_VISIT_AGREE = "10981";

    // 访问短信模版id(访问拒绝,发给访客)
    public static final String SMS_TEMP_ID_VISIT_REFUSE = "10982";

    // 访问短信模版id(访问申请，发给访客，开门密码)
    public static final String SMS_TEMP_ID_VISIT_OPEN_DOOR_PWD = "10984";

    // 访问短信模版id(访客二维码链接,开门密码)
    public static final String SMS_TEMP_ID_VISIT_QR_CODE_AND_OPEN_DOOR_PWD = "10987";

    // 访问短信模版id(访客二维码链接)
    public static final String SMS_TEMP_ID_VISIT_QR_CODE = "10988";

    // 访问短信模版id(访客随行人员二维码链接,开门密码)
    public static final String SMS_TEMP_ID_VISIT_RETINUE_QR_CODE_AND_OPEN_DOOR_PWD = "10989";

    // 访问短信模版id(访客随行人员二维码链接)
    public static final String SMS_TEMP_ID_VISIT_RETINUE_QR_CODE = "10990";

    // EXCEL导入数据起始位置
    public static final int EXCEL_IMPORT_START_POS = 4;


    /** REDIS的token标志 */
    public interface REDIS_TOKEN_SIGN {
        public static final Integer category = 98;

        /** TOKEN */
        public static final String WEB_TOKEN = "WEB_TOKEN_SIGN";
        
        /** TOKEN */
        public static final String APP_TOKEN = "APP_TOKEN_SIGN";

        /** Salt */
        public static final String SALT = "SALT_SIGN";

        /** TEMP */
        public static final String TEMP_TOKEN = "TEMP_TOKEN_SIGN";

        /** VERIF_SIGN */
        public static final String VERIF_SIGN = "VERIF_SIGN";
    }
    
    /** 状态标识 */
    public interface STATUS_SIGN {
        public static final Integer category = 100;

        /** 是 */
        public static final Integer YES = 1;

        /** 否 */
        public static final Integer NO = 0;
    }
    
    /** servlet请求方式 */
    public interface SERVLET_REQUEST_WAY {
        public static final Integer category = 93;
        /** GET */
        static final String GET = "GET";
        /** POST */
        static final String POST = "POST";
    }
    
    /** REDIS的常用数据标志 */
    public interface REDIS_DATA_SIGN {
        public static final Integer category = 98;

        /** APP */
        public static final String APP = "APP_SIGN";
        
        /** OperationType*/
        public static final String OperationType ="operationType";
        
        /** DICT */
        public static final String DICT = "DICT_SIGN";
    }
    
    /** 停启用与删除标识 */
    public interface AVAILABLE_STATUS_SIGN {
        public static final Integer category = -1;

        /** 启用 */
        public static final Integer Enable = 1;

        /** 停用 */
        public static final Integer Unable = 0;
        
        /** 删除 */
        public static final Integer Delete = -1;
    }
    
    /** 请求来源标识 */
    public interface ORGIN_SIGN {
        public static final Integer category = 1;

        /** WEB */
        public static final Integer WEB = 1;

        /** APP */
        public static final Integer APP = 0;
    }
}
