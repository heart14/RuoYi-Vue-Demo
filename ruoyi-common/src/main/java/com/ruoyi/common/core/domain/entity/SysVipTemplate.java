package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.math3.dfp.DfpField;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 会员套餐模板表 sys_vip_template
 *
 * @author wfli
 * @date 2024-02-07 13:59:12
 */
public class SysVipTemplate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐ID
     */
    private String templateId;

    /**
     * 套餐名称
     */
    @Excel(name = "套餐名称")
    private String templateName;

    /**
     * hot标题
     */
    @Excel(name = "hot标题")
    private String hotTitle;

    /**
     * 原价
     */
    @Excel(name = "原价")
    private String originPrice;

    /**
     * 划线价
     */
    @Excel(name = "划线价")
    private String realPrice;

    /**
     * VIP持续时长
     */
    @Excel(name = "VIP持续时长")
    private Long duration;

    /**
     * VIP持续时长的单位 年 | 月 | 天
     */
    @Excel(name = "VIP持续时长的单位 年 | 月 | 天")
    private String durationUnit;

    /**
     * 模板状态 0-未启用，1-使用中
     */
    @Excel(name = "模板状态 0-未启用，1-使用中")
    private Integer status;

    /**
     * 展示顺序
     */
    @Excel(name = "展示顺序")
    private Long showIndex;

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setHotTitle(String hotTitle) {
        this.hotTitle = hotTitle;
    }

    public String getHotTitle() {
        return hotTitle;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    public String getDurationUnit() {
        return durationUnit;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setShowIndex(Long showIndex) {
        this.showIndex = showIndex;
    }

    public Long getShowIndex() {
        return showIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("templateId", getTemplateId())
                .append("templateName", getTemplateName())
                .append("hotTitle", getHotTitle())
                .append("originPrice", getOriginPrice())
                .append("realPrice", getRealPrice())
                .append("duration", getDuration())
                .append("durationUnit", getDurationUnit())
                .append("status", getStatus())
                .append("showIndex", getShowIndex())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
