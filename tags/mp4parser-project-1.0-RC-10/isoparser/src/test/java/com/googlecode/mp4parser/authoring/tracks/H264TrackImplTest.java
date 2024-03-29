/*
 * Copyright 2012 Sebastian Annies, Hamburg
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * Simple test to make sure nothing breaks.
 */
public class H264TrackImplTest {

    @Test
    public void freeze() throws IOException {
        Track t = new H264TrackImpl(new BufferedInputStream(AACTrackImplTest.class.getResourceAsStream("/com/googlecode/mp4parser/authoring/tracks/h264-sample.h264")));
        Movie m = new Movie();
        m.addTrack(t);

        DefaultMp4Builder mp4Builder = new DefaultMp4Builder();
        IsoFile isoFile = mp4Builder.build(m);
//        FileChannel fc = new FileOutputStream("/home/sannies/scm/svn/mp4parser/isoparser/src/test/resources/com/googlecode/mp4parser/authoring/tracks/h264-sample.mp4").getChannel();
//        isoFile.getBox(fc);
//        fc.close();
        IsoFile isoFileReference = new IsoFile(Channels.newChannel(AACTrackImplTest.class.getResourceAsStream("/com/googlecode/mp4parser/authoring/tracks/h264-sample.mp4")));
        BoxComparator.check(isoFile, isoFileReference, Arrays.asList("/moov[0]/mvhd[0]", "/moov[0]/trak[0]/tkhd[0]", "/moov[0]/trak[0]/mdia[0]/mdhd[0]"));
    }
}
