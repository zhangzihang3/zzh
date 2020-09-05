package com.zzh.service.Impl;

import com.zzh.service.fileI;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class fileImpl implements fileI {
    /**
     * @param
     * @return
     * @function 上传文件，字节是逐个读取的
     */
    @Override
    public Boolean saveFile(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String path = "C:/a1872/" + originalFilename;
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            byte[] bytes = new byte[1024];
            int read = 0;
            fileOutputStream = new FileOutputStream(path);
            while (read != -1) {
                read = inputStream.read(bytes);
                fileOutputStream.write(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
