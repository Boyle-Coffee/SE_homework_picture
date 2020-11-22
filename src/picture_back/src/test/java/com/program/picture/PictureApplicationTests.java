package com.program.picture;

import com.program.picture.common.util.COSClientUtil;
import com.program.picture.domain.entity.Picture;
import com.program.picture.service.PictureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PictureApplicationTests {

    @Autowired
    private PictureService pictureService;

    @Test
    void contextLoads() {
    }

    @Test
    void sqlTest() {
//        Picture picture = new Picture();
//        picture.setUserId(1);
//        pictureService.insert(picture);
    }

    @Test
    void cosDelTest(){
        COSClientUtil cosClientUtil = new COSClientUtil();
        cosClientUtil.deleteFile("hyyyms-1301925880",
                "https://hyyyms-1301925880.cos.ap-guangzhou.myqcloud.com/pictureWork/13591605325285287.jpg");

    }

}
