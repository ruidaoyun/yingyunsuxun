package com.belle.yingyunsuxun.controller;

import com.belle.yingyunsuxun.model.dto.JSONResult;
import com.belle.yingyunsuxun.model.entity.PositionImg;
import com.belle.yingyunsuxun.model.entity.Shop;
import com.belle.yingyunsuxun.service.PositionImgService;
import com.belle.yingyunsuxun.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private PositionImgService positionImgService;

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    @GetMapping("{userId}")
    public List<Shop> selectShopListByUserId(@PathVariable("userId")Integer userId){
        return shopService.selectShopListByUserId (userId);
    }

    @GetMapping()
    public List<Shop> selectShopListByName(@RequestParam(value="name",required=true) String name){
        return shopService.selectShopListByShopName (name);
    }

   @PostMapping("/upload")
    public String fileUpload(@RequestParam("fileBefore") MultipartFile[] fileBeforeArray,
                             @RequestParam("fileAfter") MultipartFile[] fileAfterArray,
                             HttpServletRequest request) {
       try {
           uploadFile (fileBeforeArray,1,1,0,request);
           uploadFile (fileAfterArray,1,1,1,request);
       } catch (Exception e) {
           e.printStackTrace ();
           return JSONResult.fillResultString (1,e.getMessage (),null);
       }
       return JSONResult.fillResultString (0,"上传成功",null);
   }

   public void uploadFile(MultipartFile[] files,Integer shopId,Integer positionId,Integer status,HttpServletRequest request) throws Exception {
       for (int i=0; i < files.length; i++) {
           MultipartFile file=files[i];
           if (Objects.isNull (file) || file.isEmpty ()) {
               logger.error ("文件为空");
               throw new Exception ("文件为空，请重新上传");
               }
               try {
                   byte[] bytes=file.getBytes ();
                   UUID uuid=UUID.randomUUID ();
                   Path path=Paths.get (new File ("").getAbsolutePath ()+"/"+UPLOAD_FOLDER +"/"+ uuid +file.getOriginalFilename ().substring (file.getOriginalFilename ().lastIndexOf (".")));
                   System.out.println (path.toString ());
                   //如果没有files文件夹，则创建
                   if (!Files.isWritable (path)) {
                   Files.createDirectories (Paths.get (new File ("").getAbsolutePath ()+UPLOAD_FOLDER));
               }
               //文件写入指定路径
               Files.write (path, bytes);
               PositionImg img=new PositionImg ();
               String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/img/"+uuid+file.getOriginalFilename ().substring (file.getOriginalFilename ().lastIndexOf ("."));
               img.setShopId (shopId);
               img.setPositionId (positionId);
               img.setStatus (status);
               img.setImgPath (url);
               positionImgService.insertIntoPositionImgDao (img);
               logger.info ("文件写入成功...");
           } catch (IOException e) {
               e.printStackTrace ();
               throw new Exception ("文件上传失败");
           }
       }
   }
}
