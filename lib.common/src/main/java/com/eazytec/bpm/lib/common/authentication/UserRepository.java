package com.eazytec.bpm.lib.common.authentication;

/**
 * 用户信息仓储器
 *
 * @author ConDey
 * @version Id: UserRepository, v 0.1 2017/5/26 下午2:37 ConDey Exp $$
 */
public interface UserRepository {

    /**
     * 设置用户名
     *
     * @param username
     */
    public void saveUsername(String username);

    /**
     * 设置用户密码
     *
     * @param password
     */
    public void savePassword(String password);

    /**
     * 清空用户名
     */
    public void clearUsername();

    /**
     * 清空密码
     */
    public void clearPassword();

    /***
     * 清空所有的内容
     */
    public void clearAll();

    /**
     * 获得用户名
     *
     * @return
     */
    public String getUserName();

    /**
     * 获得密码
     *
     * @return
     */
    public String getPassword();

}
