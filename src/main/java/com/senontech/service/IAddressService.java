package com.senontech.service;

import com.senontech.entity.Address;

import java.util.List;

public interface IAddressService {
    /**
     * 添加地址数据
     * @param param
     * @return
     */
    void add(Address param);

    /**
     * 删除地址数据
     * @param addressId
     * @return
     */
    void del(Integer addressId);
    /**
     * 批量删除地址数据
     * @param addressIdList
     * @return
     */
    void delList(List<Integer> addressIdList);

    /**
     * 修改地址
     * @param param
     * @return
     */
    void edit(Address param);

    /**
     * 单个查询地址数据
     * @param param
     * @return
     */
    Address query(Address param);

    /**
     * 查询供地址据列表
     * @return
     */
    List<Address> queryList();

    /**
     * 查询供地址据列表
     * @return
     */
    List<Address> queryListByCondition(Address param);
}
