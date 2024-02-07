package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysVipTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wfli
 * @since 2024/2/7 14:01
 */
@Repository
public interface SysVipTemplateMapper {
    /**
     * 查询会员套餐模板列表
     *
     * @param templateId 会员套餐模板主键
     * @return 会员套餐模板
     */
    SysVipTemplate selectSysVipTemplateByTemplateId(String templateId);

    /**
     * 查询会员套餐模板列表
     *
     * @param sysVipTemplate 查询会员套餐模板列表
     * @return 会员套餐模板集合
     */
    List<SysVipTemplate> selectSysVipTemplateList(SysVipTemplate sysVipTemplate);

    /**
     * 新增会员套餐模板
     *
     * @param sysVipTemplate 新增会员套餐模板
     * @return 结果
     */
    int insertSysVipTemplate(SysVipTemplate sysVipTemplate);

    /**
     * 修改会员套餐模板
     *
     * @param sysVipTemplate 修改会员套餐模板
     * @return 结果
     */
    int updateSysVipTemplate(SysVipTemplate sysVipTemplate);

    /**
     * 删除会员套餐模板
     *
     * @param templateId 会员套餐模板主键
     * @return 结果
     */
    int deleteSysVipTemplateByTemplateId(String templateId);

    /**
     * 批量删除会员套餐模板
     *
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysVipTemplateByTemplateIds(String[] templateIds);
}