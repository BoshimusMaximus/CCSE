package com.ccse.cw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.ccse.cw1.db.MyUser;
import com.ccse.cw1.db.MyUserRepository;
import com.ccse.cw1.db.productRepository;
import com.ccse.cw1.db.Product;
import com.ccse.cw1.db.basketService;
import com.ccse.cw1.db.productDetailService;
import com.ccse.cw1.db.shoppingBasket;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class webController {

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private productRepository productRepository;

    @Autowired
    productDetailService pds;

    @Autowired
    private basketService basketService;

    //directs the user to the homepage
    @GetMapping("/")
    public String home() {
        return "home";
    }
    //directs the user to the homepage
    @GetMapping("/home")
    public String gethome() {
        return "home";
    }
    //directs the user to the admin homepage
    @GetMapping("/admin")
    public String getAdminHome() {
        return "adminhome";
    }

    //directs the user to the electric guitars category page
    //also adds the list of electric guitars to the model
    @GetMapping("/eGuitars")
    public String getEGuitars(Model model) {
        List<Product> products = pds.findByCategory(1);
        model.addAttribute("products", products);
        return "eGuitars";
    }
    //directs the user to the acoustic guitars category page
    //also adds the list of acoustic guitars to the model
    @GetMapping("/aGuitars")
    public String getAGuitars(Model model) {
        List<Product> products = pds.findByCategory(2);
        model.addAttribute("products", products);
        return "aGuitars";
    }
    //directs the user to the product page
    //also adds the product to the model
    @PostMapping("/product")
    public String getProduct(Model model, @RequestParam Long id) {
        Product product = pds.getProductById(id);
        System.out.println(product.getName());
        model.addAttribute("product", product);
        return "product";
    }
    //directs the user to the basket page
    @GetMapping("/basket/payment")
    public String getPayment() {
        return "payment";
    }
    //takes in user card information and processes the payment, redirects to a succesful payment page
    @PostMapping("/basket/payment/confirm")
    public String processPayment(@AuthenticationPrincipal MyUser user, @RequestParam int digits, @RequestParam int CVV, @RequestParam String expiry) {
        //processing payment is not within the scope of this project
        basketService.emptyBasket(user.getId());
        return "redirect:/basket/payment/successfulPayment";
    }
    //directs the user to the successful payment page
    @GetMapping("/basket/payment/successfulPayment")
    public String getSuccessfulPayment() {
        return "successfulPayment";
    }
    
    //directs the user to the basket page
    //also adds the list of products in the basket to the model
    //along with how many of each product there is and the total price of the basket
    @GetMapping("/basket")
    public String getBasket(@AuthenticationPrincipal MyUser user, Model model) {
        shoppingBasket basket = basketService.getBasket(user.getId());
         List<Long> productIDs = basket.getProductID();
         List<Product> products = new ArrayList<>();
         List<Integer> quantities = basket.getQuantities();
         double totalPrice = 0.0;
         for (int i = 0; i < productIDs.size(); i++) {
            Product product = pds.getProductById(productIDs.get(i));
            if (product != null) {
                products.add(product);
                totalPrice += product.getPrice() * quantities.get(i);
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("quantities", quantities);
        model.addAttribute("totalPrice", totalPrice);
        
        return "basket";
    }
    
    //adds a product to the basket then redirects to the current page
    @PostMapping("/basket/add")
    public String addToBasket(@AuthenticationPrincipal MyUser user, @RequestParam Long productID, @RequestParam String currentPage) {
        
        basketService.addProduct(user.getId(), productID, 1);
        return "redirect:"+currentPage;
    }
    //removes a product from the basket then redirects to the current page
    @PostMapping("/basket/remove")
    public String removeFromBasket(@AuthenticationPrincipal MyUser user, @RequestParam Long productID, @RequestParam String currentPage) {
        basketService.removeProduct(user.getId(), productID);
        return "redirect:"+currentPage;
    }
    //empties the basket then redirects to the current page
    @PostMapping("/basket/empty")
    public String emptyBasket(@AuthenticationPrincipal MyUser user, @RequestParam String currentPage) {
        basketService.emptyBasket(user.getId());
        return "redirect:"+currentPage+"";
    }
    
    
    //mapping for the registration page
    @GetMapping("/register")
    public String getRegistrationPage(WebRequest request, Model model) {
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "RegistrationPage";
    }

    @PostMapping("/register/success")
    //@ModelAttribute("user") receives the data from the webpage and casts it to a RegistrationDTO object
    public String registerUser(@ModelAttribute("user") @Valid RegistrationDTO rDto, Errors errors) {
        //checsks for validation errors
        if (errors.hasErrors()) {
            return "RegistrationPage";
            //reloads the registration page if there are errors
        }
        registrationController.RegisterNewAccount(rDto);
        //saves the user to the repository if there are no errors
        return "successfulRegistration";
        //directs the user to the successful registration page
    }
    //mapping for the admin/users page
    @GetMapping("/admin/users")
    public String viewUsers(Model model) {
        //creates a list of all user objects in the database
        List<MyUser> users = myUserRepository.findAll();
        //adds the list of users to the model
        model.addAttribute("users", users);
        //returns the viewUsers page with the model
        return "viewUsers";

    }

    //mapping for the admin/products page
    @PostMapping("/admin/users/delete")
    public String deleteUser(Model model, String name) {
        myUserRepository.delete(myUserRepository.findByUsername(name).orElse(null));        
        return "redirect:/admin/users";
    }

    //mapping for the admin/users page
    @PostMapping("/admin/users/addUser")
    public String postMethodName(@ModelAttribute MyUser user) {
        myUserRepository.save(user);
        return "redirect:/admin/users";
    }
    
    //mapping for the admin/products page
    @GetMapping("/admin/products")
    public String viewProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "viewProducts";
    }
    //mapping for the admin/products page
    @PostMapping("/admin/products/delete")
    public String deleteProduct(Model model, Long id) {
        System.out.println("deleting product");
        productRepository.delete(productRepository.findById(id).orElse(null));

        return "redirect:/admin/products";
    }
    //mapping for the admin/products page
    @PostMapping("/admin/products/addProduct")
    public String addProdcut(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        
        return "redirect:/admin/products";
    }
    
}
