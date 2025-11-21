package com.som.project.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.som.project.entity.BooksExcelFile;
import com.som.project.repository.BooksExcelFileRepo;
import com.som.project.service.BooksExcelUploadService;
import com.som.project.utility.Helper;

@Service
public class BooksExcelUploadServiceImpl implements BooksExcelUploadService {

	@Autowired
	BooksExcelFileRepo booksExcelFileRepo;

	@Override
	public void uploadExcelintoDB(MultipartFile file) throws IOException {

		List<BooksExcelFile> excelFilesSaveDatabase = Helper.excelFilesInsertDatabase(file.getInputStream());

		booksExcelFileRepo.saveAll(excelFilesSaveDatabase);

	}

}
