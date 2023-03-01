package com.akalji.learn.microservices.resourceservice.controller;

import com.akalji.learn.microservices.resourceservice.service.ResourceService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Nikolai_Tikhonov
 */
@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping(consumes = "audio/mpeg")
    public Map<String, Integer> createResource(InputStream audioFile) {
        var resource = resourceService.saveResource(audioFile);
        var map = new HashMap<String, Integer>();
        map.put("id", resource.getId());
        return map;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getResource(@PathVariable("id") Integer id) throws IOException {
        var resource = resourceService.getResourceById(id);
        var bytes = IOUtils.toByteArray(resource.getContent());
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentDispositionFormData("attachment", String.format("%d.mp3", resource.getId()));
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResources(@RequestParam("ids") Collection<Integer> ids) {
        resourceService.deleteResourcesByIds(new HashSet<>(ids));
    }
}
