package view;

import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.ShownBorders;
import java.util.List;

public class ConsoleView {

    public void displayProducts(List<Product> products) {
        Table table = new Table(3, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL); // Fix: Removed ShownBorders.ALL

        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Name", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Price", new CellStyle(CellStyle.HorizontalAlign.center));

        for (Product p : products) {
            table.addCell(String.valueOf(p.getId()));
            table.addCell(p.getName());
            table.addCell("$" + p.getPrice());
        }
        System.out.println("\nAvailable Products:");
        System.out.println(table.render());
    }

    public void displayCart(List<Product> cartProducts) {
        Table table = new Table(3, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Name", new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell("Price", new CellStyle(CellStyle.HorizontalAlign.center));

        for (Product p : cartProducts) {
            table.addCell(String.valueOf(p.getId()));
            table.addCell(p.getName());
            table.addCell("$" + p.getPrice());
        }
        System.out.println("\nYour Cart:");
        System.out.println(table.render());
    }
}
