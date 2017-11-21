package com.bluedon.monitor.project.service.transferTable;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.system.entity.TbLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by JiangFeng
 * on 2017/11/10.
 */
@Service("transferTableService")
public class TransferTableServiceImpl{
    @Autowired
    @Qualifier("hibernateDao")
    private IBaseDao<TbLoginLog> hibernateDao;
    public List<TransferTable> getList(Map params) {
       // String hql="select IMP_TABLE_NAME impTableName,EXP_TABLE_NAME expTableName,TABLE_COLUMN tableColumn,EXTRA_SQL extraSql,STAT stat,TABLE_TYPE tableType from transfer_table";
        String hql="select  new TransferTable(a.impTableName, a.expTableName, a.tableColumn, a.extraSql, a.stat, a.tableType)  from TransferTable a";
        if (params !=null && params.size()>0){
            hql+= " where 1=1 ";
            if(params.get("stat") != null){
                hql+=" and a.stat = :stat ";
            }
            if(params.get("tableType") != null){
                hql+=" and a.tableType = :tableType ";
            }
        }
        hql+=" order by a.impTableName";
        List<TransferTable> list = null;
        try {
            list = hibernateDao.queryForList(hql, params, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}