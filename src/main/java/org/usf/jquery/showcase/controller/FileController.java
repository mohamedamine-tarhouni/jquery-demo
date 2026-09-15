/**
 * 
 */
package org.usf.jquery.showcase.controller;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
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

        try {
            var resolver = new PathMatchingResourcePatternResolver();

            var resources = resolver.getResources(
                "classpath:/META-INF/resources/tutorials/java/**/*.md"
            );

            var mdFiles = Arrays.stream(resources)
                .map(Resource::getFilename)
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .toList();

            return ResponseEntity.ok(mdFiles);

        } catch (IOException e) {
            return ResponseEntity.ok(emptyList());
        }
    }
}
