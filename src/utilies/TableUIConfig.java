package utilies;

import model.*;
import model.dto.SaleDetailResponseDto;
import model.dto.SaleReportResponseDto;
import model.dto.SaleResponseDto;
import model.dto.UserResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;

public class TableUIConfig {
    private static final String RESET = "\033[0m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final CellStyle centerAndDot = new CellStyle(CellStyle.HorizontalAlign.center, CellStyle.AbbreviationStyle.dots);


    public static void DisplayCategory(List<Category> categoryList) {

        Table tt = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);

        tt.addCell(RED + "ID" + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        tt.addCell(RED + "Category's Name" + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);

        tt.setColumnWidth(0, 10, 50);
        tt.setColumnWidth(1, 10, 50);

        for (Category c : categoryList) {
            tt.addCell(BLUE + c.getId() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + c.getCategory_name() + RESET, centerAndDot, 1);
        }
        for (
                String t : tt.renderAsStringArray()) {
            System.out.println(t);
        }
    }

    public static void DisplayCustomer(List<Customer> customerList) {

        List<String> customerColumns = List.of("ID", "Customer's Name", "Date of Birth", "Gender");

        Table tt = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        for (String col : customerColumns) {
            tt.addCell(RED + col + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        }

        for (int i = 0; i < 4; i++) {
            tt.setColumnWidth(i, 10, 50);
        }


        for (Customer c : customerList) {
            tt.addCell(BLUE + c.getId() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + c.getCustomer_name() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + c.getCustomer_dob() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + c.getCustomer_gender() + RESET, centerAndDot, 1);
        }
        for (
                String t : tt.renderAsStringArray()) {
            System.out.println(t);
        }
    }

    public static void displayProduct(List<Product> productList) {

        List<String> productColumns = List.of("ID", "Description", "Category", "Price", "Quantity");

        Table tt = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        for (String col : productColumns) {
            tt.addCell(RED + col + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        }

        for (int i = 0; i < 5; i++) {
            tt.setColumnWidth(i, 10, 50);
        }

        for (Product p : productList) {
            tt.addCell(BLUE + p.getId() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + p.getProduct_name() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + p.getProduct_category() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + p.getProduct_price() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + p.getProduct_quantity() + RESET, centerAndDot, 1);
        }
        for (
                String t : tt.renderAsStringArray()) {
            System.out.println(t);
        }
    }

    public static void displaySale(List<SaleResponseDto> SaleList) {

        Table saleDetailTable = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE);

        List<String> saleColumns = List.of("ID", "Date", "Staff ID", "Customer ID", "Create Date","Total Sale", "Status", "Void Reason", "Void Date");

        for (String col : saleColumns) {
            saleDetailTable.addCell(RED + col + RESET, centerAndDot, 1);
        }

        for (int i = 0; i < 9; i++) {
            saleDetailTable.setColumnWidth(i, 10, 80);
        }

        for (SaleResponseDto sale : SaleList) {
            saleDetailTable.addCell(BLUE + sale.id() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.saleDate() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.staffID() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.customerID() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.saleDate() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.totalSale() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.status() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.voidReason() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + sale.voidDate() + RESET, centerAndDot, 1);
        }
        for (
                String t : saleDetailTable.renderAsStringArray()) {
            System.out.println(t);
        }

    }

    public static void displaySaleAsInvoice(SaleResponseDto saleResponseDto, List<SaleDetailResponseDto> saleDetailResponseDto) {

        Table saleDetailTable = new Table(6, BorderStyle.UNICODE_ROUND_BOX_WIDE);

        saleDetailTable.addCell(RED + "StaffID: " + saleResponseDto.staffID()
                + " ~~~ " +
                "CustomerID: " + saleResponseDto.customerID() + RESET, centerAndDot, 3);
        saleDetailTable.addCell(RED + "Sale's Date: " + saleResponseDto.saleDate() + RESET, centerAndDot, 3);

        List<String> saleDetailColumns = new ArrayList<>(List.of("No", "Product's Description", "Quantity", "Price", "Discount(%)", "Total"));

        for (String col : saleDetailColumns) {
            saleDetailTable.addCell(RED + col + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        }

        for (int k = 0; k < 6; k++) {
            saleDetailTable.setColumnWidth(k, 10, 80);
        }

        int i = 1;

        for (SaleDetailResponseDto saleDetail : saleDetailResponseDto) {
            saleDetailTable.addCell(BLUE + i++ + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + saleDetail.productName() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(GREEN + saleDetail.saleQty() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(GREEN + saleDetail.unitPrice() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(GREEN + saleDetail.discountPercent() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(GREEN + saleDetail.totalPrice() + RESET, centerAndDot, 1);
        }

        for (int j = 0; j < 7; j++) {
            saleDetailTable.addCell("", 1);
        }

        saleDetailTable.addCell("Total Items: ", centerAndDot, 1);
        saleDetailTable.addCell(String.valueOf(saleDetailResponseDto.stream().mapToInt(SaleDetailResponseDto::saleQty).sum()), centerAndDot, 1);
        saleDetailTable.addCell("", 1);
        saleDetailTable.addCell("Total Price: ", centerAndDot, 1);
        saleDetailTable.addCell(GREEN + saleDetailResponseDto.stream().mapToDouble(SaleDetailResponseDto::totalPrice).sum() + RESET, centerAndDot, 1);

        for (
                String t : saleDetailTable.renderAsStringArray()) {
            System.out.println(t);
        }
    }

    public static void displayStaff(List<Staff> staffList) {

        List<String> customerColumns = List.of("ID", "Staff's Name", "Date of Birth", "Gender", "Position", "Status");

        Table tt = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        for (String col : customerColumns) {
            tt.addCell(RED + col + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        }

        for (int i = 0; i < 6; i++) {
            tt.setColumnWidth(i, 10, 50);
        }

        for (Staff s : staffList) {
            tt.addCell(BLUE + s.getId() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + s.getStaff_name() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + s.getStaff_dob() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + s.getStaff_gender() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + s.getStaff_position() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + (s.is_working() ? "Active" : "Resigned") + RESET, centerAndDot, 1);
        }
        for (
                String t : tt.renderAsStringArray()) {
            System.out.println(t);
        }
    }

    public static void displayUser(List<UserResponseDto> userList) {
        List<String> customerColumns = List.of("UserID", "StaffID", "Username", "Role", "Status");

        Table tt = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        for (String col : customerColumns) {
            tt.addCell(RED + col + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        }

        for (int i = 0; i < 4; i++) {
            tt.setColumnWidth(i, 10, 50);
        }

        for (UserResponseDto u : userList) {
            tt.addCell(BLUE + u.userID() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + u.staffID() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + u.userName() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + u.role() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + u.status() + RESET, centerAndDot, 1);
        }
        for (
                String t : tt.renderAsStringArray()) {
            System.out.println(t);
        }
    }

    public static void displaySaleReport(List<SaleReportResponseDto> saleReportList) {
        Table saleDetailTable = new Table(3, BorderStyle.UNICODE_ROUND_BOX_WIDE);

        List<String> saleColumns = List.of("Sale Date", "Total Product Sold", "Total Revenue Earned");

        for (String col : saleColumns) {
            saleDetailTable.addCell(RED + col + RESET, centerAndDot, 1);
        }

        for (int i = 0; i < 3; i++) {
            saleDetailTable.setColumnWidth(i, 10, 80);
        }

        for (SaleReportResponseDto saleReport : saleReportList) {
            saleDetailTable.addCell(BLUE + saleReport.createDate() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + saleReport.totalProductsSold() + RESET, centerAndDot, 1);
            saleDetailTable.addCell(BLUE + saleReport.totalEarn() + RESET, centerAndDot, 1);
        }
        for (
                String t : saleDetailTable.renderAsStringArray()) {
            System.out.println(t);
        }
    }

}
