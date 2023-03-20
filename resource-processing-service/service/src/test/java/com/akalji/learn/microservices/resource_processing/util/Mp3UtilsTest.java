package com.akalji.learn.microservices.resource_processing.util;

import com.akalji.learn.microservice.song.service.common.dto.SongDto;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.io.File;
import java.io.IOException;

/**
 * @author Nikolai_Tikhonov
 */
public class Mp3UtilsTest {

    /**
     * UnitTest example
     * @throws InvalidDataException
     * @throws UnsupportedTagException
     * @throws IOException
     */
    @Test
    public void mp3MetadataExtractionTest() throws InvalidDataException, UnsupportedTagException, IOException {
        SongDto songMetadata = Mp3Utils.getSongMetadata(new File("src/test/resources/test_resource.mp3"));
        Assertions.assertEquals("Test title", songMetadata.getName());
        Assertions.assertEquals("Test artist name", songMetadata.getArtist());
        Assertions.assertEquals("test album", songMetadata.getAlbum());
        Assertions.assertEquals("0:01", songMetadata.getLength());
        Assertions.assertEquals(1998, songMetadata.getYear());
    }
}
