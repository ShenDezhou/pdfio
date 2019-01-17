package com.wwwsto.blackhole.pdfio;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

public interface API {
	default String extractText(String filename) throws InvalidPasswordException, IOException {
		File file = new File(filename);
        PDDocument pdDoc = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String content = stripper.getText(pdDoc);
        pdDoc.close();
        return content;
	}
	
	default String createPDF(String content,String filename) throws IOException {
		PDDocument pdDoc = new PDDocument();
		PDPage page  = new PDPage();
        pdDoc.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(pdDoc, page);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        int x=100,y=700;
        contentStream.beginText();
        contentStream.setFont( font, 12 );
    	contentStream.moveTextPositionByAmount( x, y );
    	contentStream.drawString(content+100);
        contentStream.endText();
        // Make sure that the content stream is closed:
        contentStream.close();
        pdDoc.save(new File(filename));
        pdDoc.close();
        return "";
	}
	
	default int saveAsImage(String filename, String imagename) throws InvalidPasswordException, IOException {
		
		File file = new File(filename);
        PDDocument pdDoc = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(pdDoc);
        int pageCounter = 0;
        String imagesuffix = "png";
        for(PDPage page : pdDoc.getPages())
        {
            // note that the page number parameter is zero based
            BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);

            // suffix in filename will be used as the file format
            ImageIO.write(bim,imagesuffix,new File(imagename+pageCounter+++"."+imagesuffix));
            
        }
        pdDoc.close();
        return 0;
	}
	
	default int splitPDF(String filename, String pdfname) throws InvalidPasswordException, IOException {
		
		File file = new File(filename);
        PDDocument pdDoc = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(pdDoc);
        int pageCounter = 0;
        String imagesuffix = "pdf";
        for(PDPage page : pdDoc.getPages())
        {
        	PDDocument _ = new PDDocument();
            _.addPage(page);
            _.save(new File(pdfname+pageCounter+++".pdf"));
            _.close();
        }
        pdDoc.close();
        return 0;
	}
	
	default int mergePDF(String pdfname, String... filename) throws InvalidPasswordException, IOException {
		PDDocument pdffile = new PDDocument();
		
		for(String file: filename) {
			PDDocument _ = PDDocument.load(new File(file));
			for(PDPage page : _.getPages()) {
				pdffile.addPage( page);
			}
		}
		pdffile.save(new File(pdfname));
		pdffile.close();
		return 0;
	}
	
	
	default int signPDF(String filename, String pdfname) throws InvalidPasswordException, IOException {
		
		File file = new File(filename);
        PDDocument pdDoc = PDDocument.load(file);
        PDSignature sigObject = new PDSignature();
        sigObject.setName("ShenDezhou");
        sigObject.setReason("Agree!");
        pdDoc.addSignature(sigObject);
        pdDoc.save(new File(pdfname));
        pdDoc.close();
        return 0;
	}
}
