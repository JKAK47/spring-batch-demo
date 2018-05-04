http://www.codejava.net/java-se/networking/ftp/java-ftp-file-upload-tutorial-and-example
https://blog.csdn.net/zlb824/article/details/7742959
https://blog.csdn.net/he90227/article/details/24458469

private byte[] downloadPDFfromFTP(String pdffilename) {
		FTPClient fc = new FTPClient();
		try {
			fc.connect(hostname);
			fc.login(username, password);
			fc.enterLocalPassiveMode();
			boolean change = fc.changeWorkingDirectory("/pdf");
			if(!change){
				throw new IMException();
			}
			InputStream fileStream = fc.retrieveFileStream(pdffilename);
			byte[] in = IOUtils.toByteArray(fileStream);
			return in;
		} catch (Exception e) {
			logger.error("下載pdf失敗");
		}finally{
			try {
				if(fc.isConnected())
					fc.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}