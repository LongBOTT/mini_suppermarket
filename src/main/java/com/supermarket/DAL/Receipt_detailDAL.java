package com.supermarket.DAL;

import com.supermarket.DTO.Receipt_detail;
import com.supermarket.utils.Date;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Receipt_detailDAL extends Manager{
    public Receipt_detailDAL() {
        super("receipt_detail",
            List.of("receipt_id",
                "product_id",
                "quantity",
                "total",
                "percent"));
    }

    public List<Receipt_detail> convertToReceipt_details(List<List<String>> data) {
        return convert(data, row -> {
            try {
                return new Receipt_detail(
                    Integer.parseInt(row.get(0)), // receipt_id
                    Integer.parseInt(row.get(1)), // product_id
                    Double.parseDouble(row.get(2)), // quantity
                    Double.parseDouble(row.get(3)), // total
                    Double.parseDouble(row.get(4))// percent
                );
            } catch (Exception e) {
                System.out.println("Error occurred in Receipt_detailDAL.convertToReceipt_details(): " + e.getMessage());
            }
            return new Receipt_detail();
        });
    }

    public int addReceipt_detail(Receipt_detail receipt_detail) {
        try {
            return create(receipt_detail.getReceipt_id(),
                receipt_detail.getProduct_id(),
                receipt_detail.getQuantity(),
                receipt_detail.getTotal(),
                receipt_detail.getPercent()
            ); // receipt_detail khi tạo mặc định deleted = 0
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Receipt_detailDAL.addReceipt_detail(): " + e.getMessage());
        }
        return 0;
    }

    public int updateReceipt_detail(Receipt_detail receipt_detail) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(receipt_detail.getReceipt_id());
            updateValues.add(receipt_detail.getProduct_id());
            updateValues.add(receipt_detail.getQuantity());
            updateValues.add(receipt_detail.getTotal());
            updateValues.add(receipt_detail.getPercent());
            return update(updateValues, "receipt_id = " + receipt_detail.getReceipt_id());
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Receipt_detailDAL.updateReceipt_detail(): " + e.getMessage());
        }
        return 0;
    }

    public int deleteReceipt_detail(String... conditions) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(true);
            return update(updateValues, conditions);
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Receipt_detailDAL.deleteReceipt_detail(): " + e.getMessage());
        }
        return 0;
    }

    public List<Receipt_detail> searchReceipt_details(String... conditions) {
        try {
            return convertToReceipt_details(read(conditions));
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Receipt_detailDAL.searchReceipt_details(): " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
