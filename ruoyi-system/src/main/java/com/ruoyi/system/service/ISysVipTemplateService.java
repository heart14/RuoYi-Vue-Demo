package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysVipTemplate;

import java.util.List;

/**
 * @author wfli
 * @since 2024/2/7 14:04
 */
public interface ISysVipTemplateService {
    /**
     * 查询会员套餐模板
     *
     * @param templateId 会员套餐模板主键
     * @return 会员套餐模板
     */
    SysVipTemplate selectSysVipTemplateByTemplateId(String templateId);

    /**
     * 查询会员套餐模板列表
     *
     * @param sysVipTemplate 会员套餐模板
     * @return 会员套餐模板集合
     */
    List<SysVipTemplate> selectSysVipTemplateList(SysVipTemplate sysVipTemplate);

    /**
     * 新增会员套餐模板
     *
     * @param sysVipTemplate 会员套餐模板
     * @return 结果
     */
    int insertSysVipTemplate(SysVipTemplate sysVipTemplate);

    /**
     * 修改会员套餐模板
     *
     * @param sysVipTemplate 会员套餐模板
     * @return 结果
     */
    int updateSysVipTemplate(SysVipTemplate sysVipTemplate);

    /**
     * 批量删除会员套餐模板
     *
     * @param templateIds 需要删除的会员套餐模板主键集合
     * @return 结果
     */
    int deleteSysVipTemplateByTemplateIds(String[] templateIds);

    /**
     * 删除会员套餐模板信息
     *
     * @param templateId 会员套餐模板主键
     * @return 结果
     */
    int deleteSysVipTemplateByTemplateId(String templateId);
}
