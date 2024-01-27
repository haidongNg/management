package com.nhd.management.services.upload;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFilesStorageService {
  void init();
  void save(MultipartFile files);
  Resource load(String filename);
  void deleteAll();
  Stream<Path> loadAll();
}
