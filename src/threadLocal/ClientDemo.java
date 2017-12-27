package threadLocal;

public class ClientDemo extends Thread {
    private ProductService productService;

    public ClientDemo(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        productService.updateProductPrice(1, 300);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ProductService productService = new ProductServiceImpl();
            ClientDemo thread = new ClientDemo(productService);
            thread.start();
        }
    }
}
