/**
 * 
 */
package org.usf.jquery.showcase.controller;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.net.JarURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.jar.JarEntry;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * READ INTO THE JSON CONTAINING ALL OF THE TUTORIALS
 */
@RestController
public class FileController {

    @GetMapping("/markdown-files")
    public ResponseEntity<List<String>> getMarkdownFiles() {

        var resource = new ClassPathResource(
                "META-INF/resources/tutorials/java"
        );

        try {

            var uri = resource.getURI();

            // Local / Eclipse
            if (uri.getScheme().equals("file")) {

                var root = Paths.get(uri);

                try (var walk = Files.walk(root)) {

                    var mdFiles = walk
                            .filter(Files::isRegularFile)
                            .filter(p -> p.toString().endsWith(".md"))
                            .map(p -> root.relativize(p)
                                    .toString()
                                    .replace("\\", "/")
                                    .toLowerCase())
                            .toList();

                    return ResponseEntity.ok(mdFiles);
                }
            }

            // Render / executable JAR
            if (uri.getScheme().equals("jar")) {

                var connection = (JarURLConnection) uri.toURL().openConnection();

                try (var jar = connection.getJarFile()) {

                    String prefix = "META-INF/resources/tutorials/java/";

                    var mdFiles = jar.stream()
                            .filter(entry -> !entry.isDirectory())
                            .map(JarEntry::getName)
                            .filter(name -> name.startsWith(prefix))
                            .filter(name -> name.endsWith(".md"))
                            .map(name -> name.substring(prefix.length())
                                    .replace("\\", "/")
                                    .toLowerCase())
                            .toList();

                    return ResponseEntity.ok(mdFiles);
                }
            }

            return ResponseEntity.ok(emptyList());

        } catch (IOException e) {
            return ResponseEntity.ok(emptyList());
        }
    }
}
