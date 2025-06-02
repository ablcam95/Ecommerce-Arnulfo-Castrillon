package com.acsolutions.arnulfocastrillon.acsolutions.application;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
public class ProductService {

    private final IProductRepository iProductRepository;
    private final UploadFile uploadFile;

    private final String URL_PREFIX = "http://localhost:8085/images/";
    private final String DEFAULT_IMAGE = "uploads/default.jpg";

    public ProductService(IProductRepository iProductRepository, UploadFile uploadFile) {
        this.iProductRepository = iProductRepository;
        this.uploadFile = uploadFile;
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if (product.getId() != null && product.getId() != 0) {
            // Producto ya existente: actualización
            if (multipartFile == null || multipartFile.isEmpty()) {
                log.info("No se proporcionó nueva imagen, se mantiene la anterior.");
                // No cambiamos imagen
            } else {
                // Se sube nueva imagen, eliminar la vieja si no es default
                deleteOldImage(product.getUrlImage());
                product.setUrlImage(uploadFile.upload(multipartFile));
            }
        } else {
            // Nuevo producto
            product.setUrlImage(uploadFile.upload(multipartFile));
        }

        return iProductRepository.save(product);
    }

    public Product update(Product product, MultipartFile multipartFile) throws IOException {
        return save(product, multipartFile);
    }

    public Iterable<Product> findByActivoTrue() {
        return iProductRepository.findByActivoTrue();
    }

    public void deleteById(Integer id) {
        Product product = findById(id);

        if (product != null) {
            deleteOldImage(product.getUrlImage());
            iProductRepository.deleteById(id);
        } else {
            log.warn("Producto con id {} no encontrado para eliminar.", id);
        }
    }

    public Product findById(Integer id) {
        return iProductRepository.findById(id);
    }

    /**
     * Elimina la imagen vieja del servidor si no es la imagen por defecto.
     */
    private void deleteOldImage(String urlImage) {
        if (urlImage != null && urlImage.startsWith(URL_PREFIX)) {
            String nameFile = urlImage.substring(URL_PREFIX.length());
            log.info("Nombre de la imagen a eliminar: {}", nameFile);

            if (!nameFile.equals(DEFAULT_IMAGE)) {
                uploadFile.delete(nameFile);
            } else {
                log.info("Imagen por defecto, no se elimina.");
            }
        }
    }
}
