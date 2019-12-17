/*
 Navicat Premium Data Transfer

 Source Server         : localmysql
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost:3306
 Source Schema         : kpaperproject

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : 65001

 Date: 16/12/2019 14:14:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for engine_factory_final_provision
-- ----------------------------
DROP TABLE IF EXISTS `engine_factory_final_provision`;
CREATE TABLE `engine_factory_final_provision`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `experiments_number` int(50) NULL DEFAULT NULL COMMENT '实验次数',
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '循环次数',
  `engine_factory_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机厂Id',
  `final_product_number` int(50) NULL DEFAULT NULL COMMENT '最终产品数量',
  `final_market_price` int(50) NULL DEFAULT NULL COMMENT '最终面向市场价格',
  `final_market_quality` int(50) NULL DEFAULT NULL COMMENT '最终质量',
  `market_need_number` int(50) NULL DEFAULT NULL COMMENT '市场需求数量',
  `actual_sale_number` int(11) NULL DEFAULT NULL COMMENT '实际卖出量(实际销售量) ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_plus
-- ----------------------------
DROP TABLE IF EXISTS `order_plus`;
CREATE TABLE `order_plus`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `experiments_number` int(50) NULL DEFAULT NULL COMMENT '实验次数',
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '循环次数',
  `engine_factory_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机厂id',
  `supplier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商id',
  `engine_to_supplier_ap` double(50, 5) NULL DEFAULT NULL COMMENT '主机厂对供应商的履约概率',
  `supplier_engine_to_ap` double(50, 5) NULL DEFAULT NULL COMMENT '供应商对主机厂的履约概率',
  `engine_whether_perform_contract` tinyint(1) NULL DEFAULT NULL COMMENT '主机厂是否履约',
  `supplier_whether_perform_contract` tinyint(1) NULL DEFAULT NULL COMMENT '供应商是否履约',
  `supplier_actual_price_p` int(50) NULL DEFAULT NULL COMMENT '服务实际价格',
  `supplier_actual_quality_qs` int(50) NULL DEFAULT NULL COMMENT '服务实际质量',
  `supplier_actual_number_m` int(50) NULL DEFAULT NULL COMMENT '服务实际数量',
  `engine_factory_to_supplier_score` int(50) NULL DEFAULT NULL COMMENT '主机厂对供应商的评分',
  `supplier_to_engine_factory_score` int(50) NULL DEFAULT NULL COMMENT '供应商对主机厂的评分',
  `engine_factory_init_credit` double(50, 5) NULL DEFAULT NULL COMMENT '主机厂每个循环开始的信誉度 ',
  `engine_factory_new_credit` double(50, 5) NULL DEFAULT NULL COMMENT '主机厂新的信誉度',
  `engine_factory_to_service_offer_price_low` int(50) NULL DEFAULT NULL COMMENT '主机厂初始期望价格_下限',
  `engine_factory_to_service_offer_price_upper` int(50) NULL DEFAULT NULL COMMENT '主机厂初始期望价格_上限',
  `supplier_init_credit` double(50, 5) NULL DEFAULT NULL COMMENT '供应商每个循环开始的信誉度',
  `supplier_new_credit` double(50, 5) NULL DEFAULT NULL COMMENT '供应商新的信誉度',
  `relationship_strength` double(50, 5) NULL DEFAULT NULL COMMENT '新的关系强度',
  `engine_factory_profit` int(50) NULL DEFAULT NULL COMMENT '主机厂利润(与供应商交易后)',
  `supplier_profit` int(50) NULL DEFAULT NULL COMMENT '供应商利润(与主机厂交易后)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 280 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商实际给主机厂提供的数量' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_balance
-- ----------------------------
DROP TABLE IF EXISTS `tb_balance`;
CREATE TABLE `tb_balance`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `experiments_number` int(50) NULL DEFAULT NULL COMMENT '实验次数',
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '循环次数',
  `engine_factory_balance` double(50, 5) NULL DEFAULT NULL COMMENT '一级市场的供需平衡情况(主机厂)',
  `supplier_balance` double(50, 5) NULL DEFAULT NULL COMMENT '二级市场的供需平衡情况(供应商)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_engine_factory
-- ----------------------------
DROP TABLE IF EXISTS `tb_engine_factory`;
CREATE TABLE `tb_engine_factory`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `experiments_number` int(50) NULL DEFAULT NULL COMMENT '实验次数',
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '循环次数',
  `engine_factory_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机厂ID',
  `engine_factory_location_g_x` int(50) NULL DEFAULT NULL COMMENT '主机厂 x 坐标',
  `engine_factory_location_g_y` int(50) NULL DEFAULT NULL COMMENT '主机厂 y 坐标',
  `engine_factory_alive` tinyint(1) NULL DEFAULT NULL COMMENT '主机厂是否存活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 221 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主机厂静态数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_engine_factory_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `tb_engine_factory_dynamic`;
CREATE TABLE `tb_engine_factory_dynamic`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '存活次数(订单id)',
  `engine_factory_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机厂ID',
  `engine_factory_total_assets_p` int(50) NULL DEFAULT NULL COMMENT '主机厂总资产p',
  `engine_factory_credit_h` double(50, 5) NULL DEFAULT NULL COMMENT '主机厂信誉度',
  `engine_factory_capacity_m` int(50) NULL DEFAULT NULL COMMENT '主机厂期望最大产能(下一阶段)',
  `engine_factory_price_p_l` int(50) NULL DEFAULT NULL COMMENT '产品期望价格下限(下一阶段)',
  `engine_factory_price_p_u` int(50) NULL DEFAULT NULL COMMENT '产品期望价格上限(下一阶段)',
  `engine_factory_quality_q` int(50) NULL DEFAULT NULL COMMENT '产品期望质量(下一阶段)',
  `engine_factory_demand_forecast_d` int(50) NULL DEFAULT NULL COMMENT '主机厂需求预测',
  `engine_factory_actual_number_m` int(50) NULL DEFAULT NULL COMMENT '主机产品实际数量(好像没用的)',
  `engine_factory_actual_price_ap` int(50) NULL DEFAULT NULL COMMENT '主机产品实际售价(好像没用的)',
  `engine_factory_actual_quality_aq` int(50) NULL DEFAULT NULL COMMENT '主机产品实际质量(好像没用的)',
  `engine_factory_capacity_utilization` double(50, 5) NULL DEFAULT NULL COMMENT '主机厂产能利用率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 209 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主机厂动态数据(每次的价格订单预测)' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_relation_matrix
-- ----------------------------
DROP TABLE IF EXISTS `tb_relation_matrix`;
CREATE TABLE `tb_relation_matrix`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `experiments_number` int(50) NULL DEFAULT NULL COMMENT '实验次数',
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '循环次数',
  `engine_factory_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机厂id',
  `supplier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商id',
  `map_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机厂id+供应商id',
  `relation_score` double(50, 5) NULL DEFAULT NULL COMMENT '目前的关系度',
  `initial_relational_degree` double(50, 5) NULL DEFAULT NULL COMMENT '初始关系度',
  `accumulative_total_score` int(50) NULL DEFAULT NULL COMMENT '双方评分之和的累加',
  `transaction_number` int(50) NULL DEFAULT NULL COMMENT '交易次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7313 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主机厂与供应商之间的关系强度矩阵' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_supplier
-- ----------------------------
DROP TABLE IF EXISTS `tb_supplier`;
CREATE TABLE `tb_supplier`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `experiments_number` int(50) NULL DEFAULT NULL COMMENT '实验次数',
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '循环次数',
  `supplier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商id',
  `supplier_location_g_x` int(50) NULL DEFAULT NULL COMMENT '供应商的地点x坐标',
  `supplier_location_g_y` int(50) NULL DEFAULT NULL COMMENT '供应商的地点y坐标',
  `supplier_type` int(50) NULL DEFAULT NULL COMMENT '服务类型(boss节点代码)',
  `supplier_fixed_cost_c` int(50) NULL DEFAULT NULL COMMENT '供应商固定成本',
  `supplier_alive` tinyint(1) NULL DEFAULT NULL COMMENT '供应商是否存活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 437 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商静态表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_supplier_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `tb_supplier_dynamic`;
CREATE TABLE `tb_supplier_dynamic`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `cycle_times` int(50) NULL DEFAULT NULL COMMENT '循环次数',
  `supplier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务提供方的id',
  `supplier_total_assets_p` int(50) NULL DEFAULT NULL COMMENT '供应商的总资产(p)',
  `supplier_credit_a` double(50, 5) NULL DEFAULT NULL COMMENT '供应商的信誉度',
  `supplier_capacity_m` int(50) NULL DEFAULT NULL COMMENT '服务最大产能',
  `supplier_price_p_l` int(50) NULL DEFAULT NULL COMMENT '服务的售价下限',
  `supplier_price_p_u` int(50) NULL DEFAULT NULL COMMENT '服务的售价上限',
  `supplier_quality_qs` int(50) NULL DEFAULT NULL COMMENT '服务质量',
  `supplier_capacity_utilization` double(50, 5) NULL DEFAULT NULL COMMENT '供应商产能利用率',
  `supplier_type` int(50) NULL DEFAULT NULL COMMENT '服务类型',
  `avg_price` int(50) NULL DEFAULT NULL COMMENT '平均价格(交易过的)',
  `avg_quality` int(50) NULL DEFAULT NULL COMMENT '平均质量(交易过的)',
  `supplier_actual_sale_number` int(50) NULL DEFAULT NULL COMMENT '服务商实际销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 437 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商每次提供的服务' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_supplier_type_avg
-- ----------------------------
DROP TABLE IF EXISTS `tb_supplier_type_avg`;
CREATE TABLE `tb_supplier_type_avg`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `experiments_number` int(50) NULL DEFAULT NULL COMMENT '实验次数',
  `cycle_times` int(11) NULL DEFAULT NULL COMMENT '循环次数',
  `supplier_type` int(50) NULL DEFAULT NULL COMMENT '提供服务类型',
  `avg_acture_price` int(50) NULL DEFAULT NULL COMMENT '每类服务的平均价格',
  `avg_acture_quality` int(50) NULL DEFAULT NULL COMMENT '每类服务的平均质量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
