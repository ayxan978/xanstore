package com.project.xanstore.controller;

import com.project.xanstore.model.CategoryModel;
import com.project.xanstore.model.ComentModel;
import com.project.xanstore.model.CounterModel;
import com.project.xanstore.model.ProductModel;
import com.project.xanstore.repository.CategoryRepository;
import com.project.xanstore.repository.ComentRepository;
import com.project.xanstore.repository.ProductRepository;
import com.project.xanstore.service.CategoryService;
import com.project.xanstore.service.ComentService;
import com.project.xanstore.service.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class XanController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ComentService comentService;
    @Autowired
    private ComentRepository comentRepository;
    @Autowired
    private SessionFactory sessionFactory;
    private ProductModel productModel;

    @PostMapping("/admin/addProduct")
    public String addProduct(Model model, @ModelAttribute ProductModel product, @RequestParam("image") MultipartFile[] image) {
        String a = "";
        for (int i = 0; i < image.length; i++) {

            try {
                saveProduct(image[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (a == "") {
                a = image[i].getOriginalFilename();
            } else {
                a = a + "," + image[i].getOriginalFilename();
            }
        }

        product.setPhoto(a);
        productRepository.save(product);
        model.addAttribute("product", new ProductModel());
        System.out.println(product);
        return "/obn";
    }

    @GetMapping("/admin/pageToAddProduct")
    public String pageToAddProduct(Model model) {
        model.addAttribute("category", new CategoryModel());
        model.addAttribute("product", new ProductModel());
        model.addAttribute("selectCategory", categoryRepository.findAll());
        model.addAttribute("counter", new CounterModel());

        return "/addAdmin";

    }

//    -------------------------
    @GetMapping("/admin/pageToEditProduct")
    public String pageToEditProduct(Model model) {
        model.addAttribute("category", new ProductModel());
        model.addAttribute("product_",productRepository.findAll());
        model.addAttribute("product", new ProductModel());
        model.addAttribute("counter", new CounterModel());

        return "/editProductAdmin";

    }

    @GetMapping("/admin/pageToEditCategory")
    public String pageToEditCategory(Model model) {
        model.addAttribute("product", new CategoryModel());
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("categoryTake", new CategoryModel());

        return "/editCategoryAdmin";

    }
    @GetMapping("/admin/open/editCategory/{id}")
    public String adminOpenCategory(
            Model model,

            @PathVariable int id) {

        model.addAttribute("CategoryOpen", categoryRepository.getById(id));
        model.addAttribute("product", new ProductModel());
        model.addAttribute("categoryTake", new CategoryModel());

        return "/formEditCategoryAdmin";
    }
    @GetMapping("/admin/open/editProduct/{id}")
    public String adminOpenProduct(
            Model model,

            @PathVariable int id) {
        model.addAttribute("productOption", categoryRepository.findAll());

        model.addAttribute("ProductOpen", productRepository.getById(id));
        model.addAttribute("product", new ProductModel());
        model.addAttribute("productTake", new ProductModel());

        return "/formEditProductAdmin";
    }
    @PostMapping("/admin/editCategory/{id}")
    public String editCategory(
            Model model, @ModelAttribute CategoryModel categoryTake,
            @RequestParam("imgCover") MultipartFile img_cover,
            @RequestParam("imgCarus") MultipartFile img_carus,
            @PathVariable int id) {


        if(img_cover.getOriginalFilename()==null||img_cover.getOriginalFilename().equals("")){

        }else{
            try {
                saveCategory(img_cover);
            } catch (IOException e) {
                e.printStackTrace();
            }
            categoryTake.setImg_cover(img_cover.getOriginalFilename());

        }
        if(img_carus.getOriginalFilename()==null||img_carus.getOriginalFilename().equals("")){

        }else{
            try {
                saveCategory(img_carus);
            } catch (IOException e) {
                e.printStackTrace();
            }
            categoryTake.setImg_carus(img_carus.getOriginalFilename());

        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();


        session.update(categoryTake);


        session.getTransaction().commit();
        session.close();
        model.addAttribute("product", new CategoryModel());
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("categoryTake", new CategoryModel());
        return "/editCategoryAdmin";
    }
    @PostMapping("/admin/editProduct/{id}")
    public String editProduct(
            Model model, @ModelAttribute ProductModel productTake,
            @RequestParam("image") MultipartFile image,
            @PathVariable int id) {


        if(image.getOriginalFilename()==null||image.getOriginalFilename().equals("")){

        }else{
            try {
                saveProduct(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            productTake.setPhoto(image.getOriginalFilename());

        }
        System.out.println(productTake);
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        session.update(productTake);


        session.getTransaction().commit();
        session.close();
        model.addAttribute("category", new CategoryModel());
        model.addAttribute("product", productRepository.findAll());
        model.addAttribute("productTake", new CategoryModel());
        return "/editProductAdmin";
    }
    private void saveCategory(MultipartFile file) throws IOException {
        byte[] fileByte = file.getBytes();
        Path path = Paths.get("/Users/Ayxan/IdeaProjects/xanstore/src/main/resources/static/imgCategory/" + file.getOriginalFilename());
        Files.write(path, fileByte);
    }

    private void saveProduct(MultipartFile file) throws IOException {
        byte[] fileByte = file.getBytes();
        Path path = Paths.get("/Users/Ayxan/IdeaProjects/xanstore/src/main/resources/static/imgProduct/" + file.getOriginalFilename());
        Files.write(path, fileByte);
    }

    @PostMapping("/admin/addCategory")
    public String addCategory(Model model, @ModelAttribute CategoryModel category, @RequestParam("imgCover") MultipartFile imgCover,
                              @RequestParam("imgCarus") MultipartFile imgCarus
    ) {

        try {
            saveCategory(imgCover);
            saveCategory(imgCarus);
        } catch (IOException e) {
            e.printStackTrace();
        }
        category.setImg_carus(imgCarus.getOriginalFilename());
        category.setImg_cover(imgCover.getOriginalFilename());
        categoryRepository.save(category);
        model.addAttribute("product", new ProductModel());
        model.addAttribute("category", new CategoryModel());
        System.out.println(category);
        return "/obn";
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("allCategoty", categoryRepository.findAll());
        model.addAttribute("selectCategory", new CategoryModel());
        model.addAttribute("product", new ProductModel());
        model.addAttribute("counter", new CounterModel());

        return "index";
    }
    @GetMapping("/open/category/{id}")
    public String openCategory(
            Model model,
            @PathVariable int id) {
        List<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(categoryRepository.getById(id));
        for (CategoryModel a : categoryModels){
            model.addAttribute("OneOfCategory_2", a.getImg_carus());
        }
        model.addAttribute("counter", new CounterModel());

        model.addAttribute("OneOfCategory", categoryRepository.getById(id));
        CategoryModel a = categoryRepository.getById(id);
        model.addAttribute("product", new ProductModel());
        System.out.println(productService.getProductOfCategory(a.getName()) + "- - - -" + a);
        model.addAttribute("products", productService.getProductOfCategory(a.getName()));
        return "/category";
    }

    @GetMapping("/delete/product/{id}")
    public String deleteProduct(
            Model model,
            @PathVariable int id) {
        model.addAttribute("product", new ProductModel());
        model.addAttribute("category", new CategoryModel());
        productRepository.deleteById(id);
        return "/obn";
    }@GetMapping("/delete/category/{id}")
    public String deleteCategory(
            Model model,
            @PathVariable int id) {
        model.addAttribute("product", new ProductModel());
        model.addAttribute("category", new CategoryModel());
        categoryRepository.deleteById(id);
        return "/obn";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute ProductModel product) {

        model.addAttribute("OneOfCategory_2", categoryService.getRandomId());
        model.addAttribute("OneOfCategory", new CategoryModel());
        model.addAttribute("products", productService.getProductOfSearch(product.getName()));
        model.addAttribute("product", new ProductModel());

        System.out.println(product);
        return "/category";
    }

    @GetMapping("/open/product/{id}")
    public String openProduct(
            Model model,
            @PathVariable int id) {
        ProductModel productModel1 = productService.getProduct(id);

        model.addAttribute("product", new ProductModel());
        model.addAttribute("comentAll", productModel1.getComentModels());
        model.addAttribute("coment", new ProductModel());
        model.addAttribute("comentTakeId", new ProductModel());

        model.addAttribute("productOpen", productRepository.getById(id));
        return "/open";
    }
    @PostMapping("/addComent")
    public String addComent(Model model, @ModelAttribute ComentModel coment, @ModelAttribute ProductModel comentTakeId) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        coment.setDate(formattedDate);


        ProductModel productModel = productService.getProduct(comentTakeId.getId());

        productModel.addComent(coment);
        coment.setProductModel(productModel);

        comentRepository.save(coment);

        productService.addProduct(productModel);

//
        ProductModel productModel1 = productService.getProduct(comentTakeId.getId());


        model.addAttribute("comentTakeId", new ProductModel());
        model.addAttribute("coment", new ProductModel());
        model.addAttribute("product", new ProductModel());
        model.addAttribute("productOpen", productRepository.getById(comentTakeId.getId()));
        model.addAttribute("comentAll", productModel1.getComentModels());
        return "/open";
    }

@GetMapping("/{id}/deleteComent")
    public String deleteComent(Model model,  @PathVariable int id){

    comentRepository.deleteById(id);

    ProductModel productModel1 = productService.getProduct(id);

    model.addAttribute("comentTakeId", new ProductModel());
    model.addAttribute("coment", new ProductModel());
    model.addAttribute("product", new ProductModel());
    model.addAttribute("productOpen", productRepository.getById(id));
    model.addAttribute("comentAll", productModel1.getComentModels());
    return "/open";
}

}
