/*
* Created by black_null on Monday 11 Dec 2023 at 5:50 PM 
* Achraf Herradi - Achrafherradi10@gmail.com
* https://github.com/black-null
*/

package com.black_null.artspace

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ArtUiTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assert_initial_displayed_image() {

        composeTestRule.apply {

            setContent { ArtSpace() }

            onNode(SemanticsMatcher.expectValue(DrawableIdKey, R.drawable.sky_cloud_1080_2400))
                .assertExists()

            onNode(SemanticsMatcher.expectValue(StringIdKey, R.string.evening_sky_clouds))
                .assertExists()
        }
    }

    @Test
    fun check_next_button() {

        composeTestRule.apply {

            setContent { ArtSpace() }

            onNodeWithText("Next").performClick()

            onNode(SemanticsMatcher.expectValue(DrawableIdKey, R.drawable.bright_landscape_1080_2400))
                .assertExists()

            onNode(SemanticsMatcher.expectValue(StringIdKey, R.string.bright_pop_landscape_design))
                .assertExists()

            onNodeWithText("Next").performClick()

            onNode(SemanticsMatcher.expectValue(DrawableIdKey, R.drawable.street_kyoto_japan_1080_2400))
                .assertExists()

            onNode(SemanticsMatcher.expectValue(StringIdKey, R.string.street_tokyo_japan))
                .assertExists()

            onNodeWithText("Next").performClick()

            onNode(SemanticsMatcher.expectValue(DrawableIdKey, R.drawable.sky_cloud_1080_2400))
                .assertExists()

            onNode(SemanticsMatcher.expectValue(StringIdKey, R.string.evening_sky_clouds))
                .assertExists()

        }
    }

    @Test
    fun check_previous_button() {

        composeTestRule.apply {
            setContent { ArtSpace() }

            onNodeWithText("Previous").performClick()

            onNode(SemanticsMatcher.expectValue(DrawableIdKey, R.drawable.street_kyoto_japan_1080_2400))
                .assertExists()

            onNode(SemanticsMatcher.expectValue(StringIdKey, R.string.street_tokyo_japan))
                .assertExists()

            onNodeWithText("Previous").performClick()

            onNode(SemanticsMatcher.expectValue(DrawableIdKey, R.drawable.bright_landscape_1080_2400))
                .assertExists()

            onNode(SemanticsMatcher.expectValue(StringIdKey, R.string.bright_pop_landscape_design))
                .assertExists()

            onNodeWithText("Previous").performClick()

            onNode(SemanticsMatcher.expectValue(DrawableIdKey, R.drawable.sky_cloud_1080_2400))
                .assertExists()

            onNode(SemanticsMatcher.expectValue(StringIdKey, R.string.evening_sky_clouds))
                .assertExists()
        }
    }


}