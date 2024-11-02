package am.itspace.eshop;

public interface ShopCom {
    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String EDIT_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_ID = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_COUNT_OF_PRODUCTS = "8";
    String PRINT_MAX_OF_PRICE_PRODUCT = "9";
    String PRINT_MIN_OF_PRICE_PRODUCT = "10";
    String PRINT_AVG_OF_PRICE_PRODUCT = "11";
    String PRINT_ALL_PRODUCT = "12";
    String PRINT_ALL_CATEGORY = "13";


    static void printCommands() {
        System.out.println("Введите '" + EXIT + "' для выхода.");
        System.out.println("Введите '" + ADD_CATEGORY + "' для добавления новой категории.");
        System.out.println("Введите '" + EDIT_CATEGORY_BY_ID + "' для редактирования категории по ID.");
        System.out.println("Введите '" + DELETE_CATEGORY_BY_ID + "' для удаления категории по ID.");
        System.out.println("Введите '" + ADD_PRODUCT + "' для добавления нового продукта.");
        System.out.println("Введите '" + EDIT_PRODUCT_BY_ID + "' для редактирования продукта по ID.");
        System.out.println("Введите '" + DELETE_PRODUCT_BY_ID + "' для удаления продукта по ID.");
        System.out.println("Введите '" + PRINT_SUM_OF_PRODUCTS + "' для вывода общего количества всех продуктов.");
        System.out.println("Введите '" + PRINT_COUNT_OF_PRODUCTS + "' для вывода количества всех продуктов.");
        System.out.println("Введите '" + PRINT_MAX_OF_PRICE_PRODUCT + "' для вывода максимальной цены продукта.");
        System.out.println("Введите '" + PRINT_MIN_OF_PRICE_PRODUCT + "' для вывода минимальной цены продукта.");
        System.out.println("Введите '" + PRINT_AVG_OF_PRICE_PRODUCT + "' для вывода средней цены продукта.");
        System.out.println("Введите '" + PRINT_ALL_PRODUCT + "' для вывода всех продуктов.");
        System.out.println("Введите '" + PRINT_ALL_CATEGORY + "' для вывода всех категорий.");
    }
}