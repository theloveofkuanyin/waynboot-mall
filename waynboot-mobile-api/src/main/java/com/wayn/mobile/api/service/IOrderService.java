package com.wayn.mobile.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.common.util.R;
import com.wayn.mobile.api.domain.Order;
import com.wayn.mobile.api.domain.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author wayn
 * @since 2020-08-11
 */
public interface IOrderService extends IService<Order> {

    /**
     * 添加订单记录
     *
     * @param orderVO 订单VO
     * @return R
     */
    R submit(OrderVO orderVO);

    /**
     * 微信H5支付
     *
     * @param orderId 订单ID
     * @param request 请求
     * @return r
     */
    R h5pay(Long orderId, HttpServletRequest request);

    /**
     * 付款订单的预支付会话标识
     * <p>
     * 1. 检测当前订单是否能够付款
     * 2. 微信商户平台返回支付订单ID
     * 3. 设置订单付款状态
     *
     * @param orderId 订单ID
     * @param request 请求
     * @return r
     */
    R prepay(Long orderId, HttpServletRequest request);

    /**
     * 获取订单列表
     * @param page      分页对象
     * @param showType  展示类型
     * @return r
     */
    R selectListPage(IPage<Order> page, Integer showType);

    /**
     * 支付成功回调处理
     * @param request  请求
     * @param response 响应
     * @return r
     */
    R payNotify(HttpServletRequest request, HttpServletResponse response);

    /**
     * 查询用户订单各状态数量（包含待支付、代发货、待收货、待评价）
     * @return r
     */
    R statusCount();
}