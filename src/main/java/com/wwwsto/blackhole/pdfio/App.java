package com.wwwsto.blackhole.pdfio;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InvalidPasswordException, IOException
    {
    	APIimp imp = new APIimp();
    	System.out.println(imp.extractText("src/main/resources/Shendezhou_CV.pdf"));
    	imp.createPDF("src/main/resources/Shendezhou_CV.pdf","src/main/resources/pdfbox.pdf");
    	imp.saveAsImage("src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV");
    	imp.signPDF("src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV``.pdf");
    	imp.splitPDF("src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV");
    	imp.mergePDF("src/main/resources/Shendezhou_CV_final.pdf","src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV1.pdf");
    }
}
