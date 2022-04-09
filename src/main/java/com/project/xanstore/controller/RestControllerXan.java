package com.project.xanstore.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/home/rest")
public class RestControllerXan {
    @GetMapping(
            value = "/img/{userPicture}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageForCategory(
            @PathVariable("userPicture") String userPicture
    ) {
        try{
            System.out.println(userPicture);
            File file = new File("/Users/Ayxan/IdeaProjects/xanstore/src/main/resources/static/imgCategory/" + userPicture);

                    InputStream in = new FileInputStream(file);
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException fex){
            System.out.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @GetMapping(
            value = "/img2/{userPicture}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageForProduct(
            @PathVariable("userPicture") String userPicture
    ) {
        try{
            System.out.println(userPicture);
            File file = new File("/Users/Ayxan/IdeaProjects/xanstore/src/main/resources/static/imgProduct/" + userPicture);

            InputStream in = new FileInputStream(file);
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException fex){
            System.out.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
