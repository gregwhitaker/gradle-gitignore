package com.github.gregwhitaker.gitignore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GitIgnoreExtensionTest {

    @Test
    public void autoDetectShouldDefaultToTrue() {
        // Given
        final GitIgnoreExtension extension = new GitIgnoreExtension();

        // Then
        assertTrue(extension.isAutoDetect());
    }

    @Test
    public void urlShouldDefaultToNull() {
        // Given
        final GitIgnoreExtension extension = new GitIgnoreExtension();

        // Then
        assertNull(extension.getUrl());
    }

    @Test
    public void facetsShouldDefaultToEmptyList() {
        // Given
        final GitIgnoreExtension extension = new GitIgnoreExtension();

        // Then
        assertTrue(extension.getFacets().isEmpty());
    }
}
