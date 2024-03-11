package cn.showsi.server.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.showsi.server.mapper.WhitelistInfoMapper;
import cn.showsi.server.domain.WhitelistInfo;
import cn.showsi.server.service.IWhitelistInfoService;

/**
 * 白名单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-26
 */
@Service
public class WhitelistInfoServiceImpl implements IWhitelistInfoService 
{
    @Autowired
    private WhitelistInfoMapper whitelistInfoMapper;

    /**
     * 查询白名单
     * 
     * @param id 白名单主键
     * @return 白名单
     */
    @Override
    public WhitelistInfo selectWhitelistInfoById(Long id)
    {
        return whitelistInfoMapper.selectWhitelistInfoById(id);
    }

    /**
     * 查询白名单列表
     * 
     * @param whitelistInfo 白名单
     * @return 白名单
     */
    @Override
    public List<WhitelistInfo> selectWhitelistInfoList(WhitelistInfo whitelistInfo)
    {
        return whitelistInfoMapper.selectWhitelistInfoList(whitelistInfo);
    }

    /**
     * 新增白名单
     * 
     * @param whitelistInfo 白名单
     * @return 结果
     */
    @Override
    public int insertWhitelistInfo(WhitelistInfo whitelistInfo)
    {
        return whitelistInfoMapper.insertWhitelistInfo(whitelistInfo);
    }

    /**
     * 修改白名单
     * 
     * @param whitelistInfo 白名单
     * @return 结果
     */
    @Override
    public int updateWhitelistInfo(WhitelistInfo whitelistInfo)
    {
        return whitelistInfoMapper.updateWhitelistInfo(whitelistInfo);
    }

    /**
     * 批量删除白名单
     * 
     * @param ids 需要删除的白名单主键
     * @return 结果
     */
    @Override
    public int deleteWhitelistInfoByIds(Long[] ids)
    {
        return whitelistInfoMapper.deleteWhitelistInfoByIds(ids);
    }

    /**
     * 删除白名单信息
     * 
     * @param id 白名单主键
     * @return 结果
     */
    @Override
    public int deleteWhitelistInfoById(Long id)
    {
        return whitelistInfoMapper.deleteWhitelistInfoById(id);
    }
}
