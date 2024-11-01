package am.itspace.eshop;

import am.itspace.model.Category;
import am.itspace.model.Product;
import am.itspace.services.CategoryService;

public class Shop implements ShopCom {
    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            ShopCom.printCommands();
            switch (scanner.nextLine()) {
                case EXIT -> isRun = false;
                case ADD_CATEGORY -> cs.add(addCategory());
                case EDIT_CATEGORY_BY_ID -> editCategory();
                case DELETE_CATEGORY_BY_ID -> cs.deleteCategory(deleteCategory());
                case ADD_PRODUCT -> ps.add(addProduct());
                case EDIT_PRODUCT_BY_ID -> editProduct();
                case DELETE_PRODUCT_BY_ID -> ps.deleteProduct(deleteProduct());
                case PRINT_SUM_OF_PRODUCTS -> System.out.println(ps.absoluteQuantity());
                case PRINT_COUNT_OF_PRODUCTS -> System.out.println(ps.countProducts());
                case PRINT_MAX_OF_PRICE_PRODUCT -> System.out.println(ps.getMaxPrice());
                case PRINT_MIN_OF_PRICE_PRODUCT -> System.out.println(ps.getMinPrice());
                case PRINT_AVG_OF_PRICE_PRODUCT -> System.out.println(ps.getAveragePrice());
                default -> System.out.println("Неправильная команда");
            }
        }
    }

    private static int deleteProduct() {
        while (true) {
            try {
                System.out.println(ps.getAllProduct());
                System.out.print("Выберите ID: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неправильно, попробуйте еще раз.");
            }
        }
    }

    private static void editProduct() {
        while (true) {
            try {
                System.out.println(ps.getAllProduct());
                System.out.print("Выберите ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Пожалуйста, введите новое имя, описание, цену, количество и ID категории:");
                cs.getAllCategory();
                String productStr = scanner.nextLine();
                String[] productArr = productStr.split(",");
                ps.editProduct(id,
                        productArr[0],
                        productArr[1],
                        Double.parseDouble(productArr[2]),
                        Integer.parseInt(productArr[3]),
                        CategoryService.getCategoryById(Integer.parseInt(productArr[4])));
                return;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неправильно, попробуйте еще раз.");
            }
        }
    }

    private static Product addProduct() {
        while (true) {
            try {
                System.out.println("Пожалуйста, введите имя, описание, цену, количество и ID категории:");
                System.out.println(cs.getAllCategory());
                String productStr = scanner.nextLine();
                String[] productArr = productStr.split(",");
                return Product.builder()
                        .name(productArr[0])
                        .description(productArr[1])
                        .price(Double.parseDouble(productArr[2]))
                        .quantity(Integer.parseInt(productArr[3]))
                        .category(CategoryService.getCategoryById(Integer.parseInt(productArr[4])))
                        .build();
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неправильно, попробуйте еще раз.");
            }
        }
    }

    private static int deleteCategory() {
        while (true) {
            try {
                System.out.println(cs.getAllCategory());
                System.out.print("Выберите ID: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неправильно, попробуйте еще раз.");
            }
        }
    }

    private static void editCategory() {
        while (true) {
            try {
                System.out.println(cs.getAllCategory());
                System.out.print("Выберите ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Введите новое имя категории: ");
                String name = scanner.nextLine();
                cs.editCategory(name, id);
                return;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неправильно, попробуйте еще раз.");
            }
        }
    }

    private static Category addCategory() {
        System.out.print("Введите имя категории: ");
        return Category.builder()
                .name(scanner.nextLine())
                .build();
    }
}