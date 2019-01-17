#Create PDFs
Create a PDF from scratch, with embedded fonts and images.

APIimp imp = new APIimp();
      
#Extract Text
Extract Unicode text from PDF files.
    	System.out.println(imp.extractText("src/main/resources/Shendezhou_CV.pdf"));

#Split a single PDF into many files or merge multiple PDF files.
    	imp.splitPDF("src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV");
    	imp.mergePDF("src/main/resources/Shendezhou_CV_final.pdf","src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV1.pdf");

#Save as Image
Save PDFs as image files, such as PNG or JPEG.
    	imp.saveAsImage("src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV");

#Signing
Digitally sign PDF files.
    	imp.signPDF("src/main/resources/Shendezhou_CV.pdf","src/main/resources/Shendezhou_CV``.pdf");

