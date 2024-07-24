package com.javalab.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javalab.board.service.ProductService;
import com.javalab.board.vo.ImgVo;
import com.javalab.board.vo.ProductVo;
import com.javalab.board.vo.ProductWithImageVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

	// servlet-context.xml에 설정되어 있는 file.properties 파일에 설정해놓은 file.path의 값을 읽어온다.
	// 읽어온 값을 filePath 변수에 저장한다.
    @Value("${file.path}")
    private String filePath;

    // 서블릿 컨텍스트 의존성 주입 : 모든 애플리케이션당 하나씩 생성되며 앱에 관한 모든 정보를 갖고 있다.
    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public String create() {
        return "/product/productCreate";
    }

    /**
     * 상품 저장 메소드
     * @param productVo : 화면에서 입력한 상품 정보
     * @param files : 상품의 파일들 첨부파일
     * @param model
     * @return
     */
    @PostMapping("/create")
    public String handleFileUpload(ProductVo productVo, 
                                   @RequestParam("files") ArrayList<MultipartFile> files, 
                                   Model model) {

    	log.info("productVo 화면에서 받은 값 : " + productVo);
    	// 1. 상품 저장
        productService.addProduct(productVo);

        log.info("productVo 상품 등록후 : " + productVo);
        
        Long productId = productVo.getProductId(); // 여기서 productId가 설정됨
        
        // 파일 저장 경로 생성 메소드 호출(년/월/일)
        String uploadFolderPath = getFolder();
        String uploadPath = servletContext.getRealPath("/") + filePath + File.separator + uploadFolderPath;

        // 년/월/일까지 포함된 경로가 생성된다.
        File uploadFilePath = new File(uploadPath);
        if (!uploadFilePath.exists()) {
            uploadFilePath.mkdirs();
        }
        // 화면에서 여러개 전달된 이미지를 저장할 객체
        List<ImgVo> imageList = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            MultipartFile multipartFile = files.get(i);
            if (!multipartFile.isEmpty()) {
                try {
                    String uploadFileName = multipartFile.getOriginalFilename();
                    // 파일명 추출
                    uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
                    // 유니크한 번호를 만들어 주는 유티리티 객체
                    UUID uuid = UUID.randomUUID();
                    uploadFileName = uuid.toString() + "_" + uploadFileName;
                    File saveFile = new File(uploadFilePath, uploadFileName);

                    if (checkImageType(saveFile)) {
                    	// 물리적으로 이미지 파일 업로드
                        multipartFile.transferTo(saveFile);
                        // 이미지 파일명 저장 작업
                        ImgVo image = new ImgVo();
                        image.setProductId(productId);
                        image.setImgPath(uploadFolderPath);
                        image.setFileName(uploadFileName);
                        image.setIsMain(i == 0 ? 1 : 0); // 첫 번째 이미지를 대표 이미지로 설정
                        // 이미지 ArrayList에 저장
                        imageList.add(image);
                    } else {
                        return "uploadFailure"; // 이미지 파일이 아닌 경우
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    return "uploadFailure";
                }
            }
        }
        // 이미지 ArrayList 있으면
        if (!imageList.isEmpty()) {
            productService.insertImages(imageList); // 이미지 저장
        }

        model.addAttribute("name", productVo.getName());
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<ProductWithImageVo> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        return "/product/productList";
    }

    /**
     * 년/월/일 형태로 된 파일 객체 생성할 때 사용할 문자열 생성
     * @return
     */
    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", File.separator);
    }

    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            return contentType != null && contentType.startsWith("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 상품 상세 정보
     * - 상품 한개와 여러개의 이미지 출력
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/detail/{productId}")
    public String productDetail(@PathVariable("productId") Long productId, Model model) {
        ProductWithImageVo productWithImages = productService.getProductWithAllImages(productId);
        model.addAttribute("productWithImageVo", productWithImages);
        return "/product/productDetail";
    }
}
