package br.com.gerenciadorproposta.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtil {

    public void copy(File sourceFile, String destinationPath) {
       try {
           File destinationFile = new File(destinationPath);

           FileUtils.copyFile(sourceFile, destinationFile);
       } catch (IOException e) {
           throw new RuntimeException("Erro ao copiar o arquivo", e);
       }
    }

}
