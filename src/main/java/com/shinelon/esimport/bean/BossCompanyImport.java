package com.shinelon.esimport.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.shinelon.esimport.converter.LocalDateStringConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体公司导入
 * </p>
 *
 * @author mybatis-plus
 * @since 2022-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BossCompanyImport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
//    @ExcelProperty("id")
    @ExcelProperty(index = 0)
    private Long id;

    /**
     * 逻辑删除 删除状态：0 正常，1 删除
     */
//    @ExcelProperty("is_deleted")
    @ExcelProperty(index = 1)
    private Integer isDeleted;

    /**
     * 创建时间
     */
//    @ExcelProperty("create_time")
    @ExcelProperty(index = 2)
    @DateTimeFormat("yyyy/M/d H:m:s")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
//    @ExcelProperty("create_user_id")
    @ExcelProperty(index = 3)
    private String createUserId;

    /**
     * 更新时间
     */
//    @ExcelProperty("update_time")
    @ExcelProperty(index = 4)
    @DateTimeFormat("yyyy/M/d H:m:s")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
//    @ExcelProperty("update_user_id")
    @ExcelProperty(index = 5)
    private String updateUserId;

    /**
     * 实体公司ID
     */
//    @ExcelProperty("company_entity_id")
    @ExcelProperty(index = 6)
    private Integer companyEntityId;

    /**
     * 实体公司名
     */
//    @ExcelProperty("company_entity_name")
    @ExcelProperty(index = 7)
    private String companyEntityName;

    /**
     * 企业名称
     */
//    @ExcelProperty("enterprise_name")
    @ExcelProperty(index = 8)
    private String enterpriseName;

    /**
     * 登记状态
     */
//    @ExcelProperty("registration_status")

    @ExcelProperty(index = 9)
    private String registrationStatus;

    /**
     * 法定代表人
     */
//    @ExcelProperty("legal_representative")
    @ExcelProperty(index = 10)
    private String legalRepresentative;


    /**
     * 注册资本
     */
//    @ExcelProperty("registered_capital")
    @ExcelProperty(index = 11)
    private String registeredCapital;

    /**
     * 成立日期
     */
//    @ExcelProperty("incorporation_date")
    @ExcelProperty(index = 12,converter = LocalDateStringConverter.class)
    @DateTimeFormat("yyyy/M/d")
    private LocalDate incorporationDate;

    /**
     * 核准日期
     */
//    @ExcelProperty("approval_date")
    @ExcelProperty(index = 13,converter = LocalDateStringConverter.class)
    @DateTimeFormat("yyyy/M/d")
    private LocalDate approvalDate;

    /**
     * 所属省份
     */
//    @ExcelProperty("province")
    @ExcelProperty(index = 14)
    private String province;

    /**
     * 省编码
     */
//    @ExcelProperty("province_code")
    @ExcelProperty(index = 15)
    private Integer provinceCode;

    /**
     * 所属城市
     */
//    @ExcelProperty("city")
    @ExcelProperty(index = 16)
    private String city;

    /**
     * 市编码
     */
//    @ExcelProperty("city_code")
    @ExcelProperty(index = 17)
    private Integer cityCode;

    /**
     * 所属区县
     */
//    @ExcelProperty("district")
    @ExcelProperty(index = 18)
    private String district;

    /**
     * 区编码
     */
//    @ExcelProperty("district_code")
    @ExcelProperty(index = 19)
    private Integer districtCode;

    /**
     * 电话
     */
//    @ExcelProperty("telephone")
    @ExcelProperty(index = 20)
    private String telephone;

    /**
     * 更多电话
     */
//    @ExcelProperty("more_telephone")
    @ExcelProperty(index = 21)
    private String moreTelephone;

    /**
     * 邮箱
     */
//    @ExcelProperty("mail")
    @ExcelProperty(index = 22)
    private String mail;

    /**
     * 更多邮箱
     */
//    @ExcelProperty("more_mail")
    @ExcelProperty(index = 23)
    private String moreMail;

    /**
     * 统一社会信用代码
     */
//    @ExcelProperty("social_code")
    @ExcelProperty(index = 24)
    private String socialCode;

    /**
     * 纳税人识别号
     */
//    @ExcelProperty("taxpayer_code")
    @ExcelProperty(index = 25)
    private String taxpayerCode;

    /**
     * 注册号
     */
//    @ExcelProperty("registration_code")
    @ExcelProperty(index = 26)
    private String registrationCode;

    /**
     * 组织机构代码
     */
//    @ExcelProperty("organization_code")
    @ExcelProperty(index = 27)
    private String organizationCode;

    /**
     * 参保人数
     */
//    @ExcelProperty("insured_count")
    @ExcelProperty(index = 28)
    private String insuredCount;

    /**
     * 企业类型
     */
//    @ExcelProperty("enterprise_type")
    @ExcelProperty(index = 29)
    private String enterpriseType;

    /**
     * 所属行业
     */
//    @ExcelProperty("industry")
    @ExcelProperty(index = 30)
    private String industry;

    /**
     * 曾用名
     */
//    @ExcelProperty("name_used")
    @ExcelProperty(index = 31)
    private String nameUsed;

    /**
     * 英文名
     */
//    @ExcelProperty("english_name")
    @ExcelProperty(index = 32)
    private String englishName;

    /**
     * 网址
     */
//    @ExcelProperty("website")
    @ExcelProperty(index = 33)
    private String website;

    /**
     * 企业地址
     */
//    @ExcelProperty("enterprise_address")
    @ExcelProperty(index = 34)
    private String enterpriseAddress;

    /**
     * 最新年报地址
     */
//    @ExcelProperty("latest_annual_report_address")
    @ExcelProperty(index = 35)
    private String latestAnnualReportAddress;

    /**
     * 经营范围
     */
//    @ExcelProperty("business_scope")
    @ExcelProperty(index = 36)
    private String businessScope;

    /**
     * 数据来源1企查查
     */
//    @ExcelProperty("source")
    @ExcelProperty(index = 37)
    private Integer source;

    /**
     * 拓展字段1
     */
//    @ExcelProperty("ext_1")
    @ExcelProperty(index = 38)
    private String ext1;

    /**
     * 拓展字段2
     */
    @ExcelProperty(index = 39)
    private String ext2;

    /**
     * 拓展字段3
     */
//    @ExcelProperty("ext_3")
    @ExcelProperty(index = 40)
    private String ext3;


}
