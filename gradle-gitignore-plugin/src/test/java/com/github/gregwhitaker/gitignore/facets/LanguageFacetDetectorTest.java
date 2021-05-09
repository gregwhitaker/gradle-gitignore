/**
 * Copyright 2017-Present Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.gregwhitaker.gitignore.facets;

import org.gradle.api.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LanguageFacetDetectorTest {

    @Mock
    private Project mockProject;

    @Test
    public void shouldHaveCorrectPluginToFacetMappings() {
        // Given
        LanguageFacetDetector detector = new LanguageFacetDetector(mockProject);

        // When
        Map<String, List<String>> mappings = detector.pluginToFacetsMappings();

        // Then
        assertEquals(5, mappings.size());

        assertEquals(1, mappings.get("java").size());
        assertEquals("java", mappings.get("java").get(0));

        assertEquals(1, mappings.get("java-library").size());
        assertEquals("java", mappings.get("java-library").get(0));

        assertEquals(1, mappings.get("java-platform").size());
        assertEquals("java", mappings.get("java-platform").get(0));

        assertEquals(1, mappings.get("groovy").size());
        assertEquals("java", mappings.get("groovy").get(0));

        assertEquals(1, mappings.get("grails").size());
        assertEquals("grails", mappings.get("grails").get(0));
    }
}
