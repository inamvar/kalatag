package com.semsari.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.semsari.domain.Item;

import com.semsari.service.DealService;

@Controller
@RequestMapping(value = "/files")
public class FilesController {


	
	@Autowired
	DealService dealService;

	@Autowired
    ServletContext context;

	@RequestMapping(value = "/deals/{id}/image", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource  getItemThumbnail(@PathVariable("id") int id,@RequestParam("type") int type, @RequestParam("width") int width, @RequestParam("height") int height, HttpServletResponse response, Model model) {
       Item item = dealService.find(id);
        if (item != null) {
            byte[] imageInByte;
            switch (type){
                case 0:
                    imageInByte =    item.getThumbnail();
                    break;
                case 1:
                    imageInByte =    item.getImage1();
                    break;
                case 2:
                    imageInByte =    item.getImage2();
                    break;
                case 3:
                    imageInByte =    item.getImage3();
                    break;
                case 4:
                    imageInByte =    item.getImage4();
                    break;
                default:
                    imageInByte = null;
                    break;
            }


            if (imageInByte != null && imageInByte.length > 0) {
                try {
                    response.setContentType("image/jpeg");
                    OutputStream out = response.getOutputStream();
                    ByteArrayInputStream ins = new ByteArrayInputStream(imageInByte);
                    BufferedImage scaledImg = Scalr.resize(ImageIO.read(ins), Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, width, height);
                    ImageIO.write(scaledImg, "jpg", out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(type == 0){
                try {

                    response.setContentType("image/jpeg");
                    OutputStream out = response.getOutputStream();
                    InputStream in = context.getResourceAsStream("/resources/images/logo.jpg");
                    imageInByte =  IOUtils.toByteArray(in);
                    ByteArrayInputStream ins = new ByteArrayInputStream(imageInByte);
                    BufferedImage scaledImg = Scalr.resize(ImageIO.read(ins), Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, width, height);
                    ImageIO.write(scaledImg, "jpg", out);
                    out.flush();
                }catch (Exception e){
                    return null;
                }

            }
        }
        return null;
    }
	

	
}
