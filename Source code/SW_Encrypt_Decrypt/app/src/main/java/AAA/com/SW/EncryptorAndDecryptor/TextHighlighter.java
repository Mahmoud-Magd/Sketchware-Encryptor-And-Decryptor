package AAA.com.SW.EncryptorAndDecryptor;
/*

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TextHighlighter {
    private TextView textView;
    private List<Integer> matchIndexes = new ArrayList<>();
    private int currentIndex = -1;

    public TextHighlighter(TextView textView) {
        this.textView = textView;
    }

    // Search and highlight all occurrences without removing existing spans
    public void searchAndHighlight(String query) {
        resetHighlight(); // Clear previous highlights (but not other spans)
        if (query.isEmpty()) return;

        CharSequence latestText = textView.getText();
        SpannableString spannable = latestText instanceof Spannable ? (SpannableString) latestText : new SpannableString(latestText);
        String lowerText = latestText.toString().toLowerCase();
        String lowerQuery = query.toLowerCase();

        int index = lowerText.indexOf(lowerQuery);
        while (index != -1) {
            matchIndexes.add(index);
            spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), index, index + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            index = lowerText.indexOf(lowerQuery, index + 1);
        }

        textView.setText(spannable);
        if (!matchIndexes.isEmpty()) currentIndex = 0; // Reset navigation to first match
    }

    // Move to the next match
    public void nextMatch(String query) {
        if (matchIndexes.isEmpty()) return;

        currentIndex = (currentIndex + 1) % matchIndexes.size();
        highlightCurrentMatch(query);
    }

    // Move to the previous match
    public void previousMatch(String query) {
        if (matchIndexes.isEmpty()) return;

        currentIndex = (currentIndex - 1 + matchIndexes.size()) % matchIndexes.size();
        highlightCurrentMatch(query);
    }

    // Highlight only the currently selected match without removing other spans
    private void highlightCurrentMatch(String query) {
        Spannable spannable = new SpannableString(textView.getText());

        for (int index : matchIndexes) {
            spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), index, index + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // Highlight the current match in ORANGE
        int currentPos = matchIndexes.get(currentIndex);
        spannable.setSpan(new BackgroundColorSpan(Color.rgb(255, 165, 0)), currentPos, currentPos + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannable);
    }

    // Reset search highlights without removing other spans
    public void resetHighlight() {
        Spannable spannable = new SpannableString(textView.getText());

        // Remove only the yellow and orange highlights
        BackgroundColorSpan[] spans = spannable.getSpans(0, spannable.length(), BackgroundColorSpan.class);
        for (BackgroundColorSpan span : spans) {
            int color = span.getBackgroundColor();
            if (color == Color.YELLOW || color == Color.rgb(255, 165, 0)) {
                spannable.removeSpan(span);
            }
        }

        textView.setText(spannable);
        matchIndexes.clear();
        currentIndex = -1;
    }

    // Returns the number of matches found
    public int getMatchCount() {
        return matchIndexes.size();
    }
}
*/

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHighlighter {
    private TextView textView;
    private List<Integer> matchIndexes = new ArrayList<>();
    private int currentIndex = -1;
    private String currentQuery = "";
    private boolean caseSensitive = false;
    private boolean useRegex = false;
    private boolean wholeWord = false;

    public TextHighlighter(TextView textView) {
        this.textView = textView;
    }

    // Enable or disable case-sensitive search
    public void setCaseSensitive(boolean enabled) {
        this.caseSensitive = enabled;
    }

    // Enable or disable regex search
    public void setUseRegex(boolean enabled) {
        this.useRegex = enabled;
    }

    // Enable or disable whole-word search
    public void setWholeWord(boolean enabled) {
        this.wholeWord = enabled;
    }

    // Search and highlight all occurrences
    public void searchAndHighlight(String query, boolean resetIndex) {
        resetHighlight(); // Clear previous highlights (but not other spans)
        if (query.isEmpty()) return;

        this.currentQuery = query;
        CharSequence latestText = textView.getText();
        SpannableString spannable = latestText instanceof Spannable ? (SpannableString) latestText : new SpannableString(latestText);

        String patternString = query;
        if (!useRegex) {
            patternString = Pattern.quote(query); // Escape special regex characters if regex is disabled
        }

        if (wholeWord) {
            patternString = "\\b" + patternString + "\\b"; // Ensure whole-word matching
        }

        Pattern pattern = caseSensitive ? Pattern.compile(patternString) : Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(latestText);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            matchIndexes.add(start);
            spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(spannable);
        // if (!matchIndexes.isEmpty()) currentIndex = -1; // Reset navigation to first match
        if ( resetIndex || currentIndex > getMatchCount() ) currentIndex = -1;
    }

    // Move to the next match
    public void nextMatch() {
        if (matchIndexes.isEmpty()) return;

        currentIndex = (currentIndex + 1) % matchIndexes.size();
        highlightCurrentMatch();
    }

    // Move to the previous match
    public void previousMatch() {
        if (matchIndexes.isEmpty()) return;

        currentIndex = (currentIndex - 1 + matchIndexes.size()) % matchIndexes.size();
        highlightCurrentMatch();
    }
    
    // Return current index
    public int currentIndex() {
        return currentIndex;
    }

    // Highlight only the currently selected match without removing other spans
    private void highlightCurrentMatch() {
        Spannable spannable = new SpannableString(textView.getText());

        for (int index : matchIndexes) {
            spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), index, index + currentQuery.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // Highlight the current match in ORANGE
        int currentPos = matchIndexes.get(currentIndex);
        spannable.setSpan(new BackgroundColorSpan(Color.rgb(255, 165, 0)), currentPos, currentPos + currentQuery.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannable);
    }

    // Reset search highlights without removing other spans
    public void resetHighlight() {
        Spannable spannable = new SpannableString(textView.getText());

        // Remove only the yellow and orange highlights
        BackgroundColorSpan[] spans = spannable.getSpans(0, spannable.length(), BackgroundColorSpan.class);
        for (BackgroundColorSpan span : spans) {
            int color = span.getBackgroundColor();
            if (color == Color.YELLOW || color == Color.rgb(255, 165, 0)) {
                spannable.removeSpan(span);
            }
        }

        textView.setText(spannable);
        matchIndexes.clear();
        currentIndex = -1;
    }

    // Returns the number of matches found
    public int getMatchCount() {
        return matchIndexes.size();
    }
}


/*
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TextHighlighter {
    private TextView textView;
    private ScrollView vScroll;
    private HorizontalScrollView hScroll;
    private List<Integer> matchIndexes = new ArrayList<>();
    private int currentIndex = -1;
    private String originalText = ""; // Store original text

    public TextHighlighter(TextView textView, ScrollView vScroll, HorizontalScrollView hScroll) {
        this.textView = textView;
        this.vScroll = vScroll;
        this.hScroll = hScroll;
        this.originalText = textView.getText().toString(); // Store initial text
    }

    // Search and highlight all occurrences
    public void searchAndHighlight(String query) {
        resetHighlight(); // Clear previous highlights
        if (query.isEmpty()) return;

        originalText = textView.getText().toString(); // Store original text again before modifying
        SpannableStringBuilder spannable = new SpannableStringBuilder(originalText);
        String lowerText = originalText.toLowerCase();
        String lowerQuery = query.toLowerCase();

        int index = lowerText.indexOf(lowerQuery);
        while (index != -1) {
            matchIndexes.add(index);
            spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), index, index + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            index = lowerText.indexOf(lowerQuery, index + 1);
        }

        textView.setText(spannable);
        if (!matchIndexes.isEmpty()) {
            currentIndex = 0; // Reset navigation to first match
            scrollToCurrentMatch(query);
        }
    }

    // Move to the next match
    public void nextMatch(String query) {
        if (matchIndexes.isEmpty()) return;

        currentIndex = (currentIndex + 1) % matchIndexes.size();
        highlightCurrentMatch(query);
        scrollToCurrentMatch(query);
    }

    // Move to the previous match
    public void previousMatch(String query) {
        if (matchIndexes.isEmpty()) return;

        currentIndex = (currentIndex - 1 + matchIndexes.size()) % matchIndexes.size();
        highlightCurrentMatch(query);
        scrollToCurrentMatch(query);
    }

    // Highlight only the currently selected match
    private void highlightCurrentMatch(String query) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(textView.getText());

        // Reapply yellow highlight to all matches
        for (int index : matchIndexes) {
            spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), index, index + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // Highlight the current match in ORANGE
        int currentPos = matchIndexes.get(currentIndex);
        spannable.setSpan(new BackgroundColorSpan(Color.rgb(255, 165, 0)), currentPos, currentPos + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannable);
    }

    // Scroll to the current highlighted match
    private void scrollToCurrentMatch(String query) {
        if (matchIndexes.isEmpty() || currentIndex == -1) return;

        int currentPos = matchIndexes.get(currentIndex);
        int lineOffset = textView.getLayout().getLineForOffset(currentPos); // Get line number of match

        // Calculate Y position to scroll to
        int targetY = textView.getLayout().getLineTop(lineOffset);
        vScroll.smoothScrollTo(0, targetY);

        // Calculate X position for horizontal scrolling
        int targetX = (int) textView.getLayout().getPrimaryHorizontal(currentPos);
        hScroll.smoothScrollTo(targetX, 0);
    }

    // Reset the text to remove highlights
    public void resetHighlight() {
        textView.setText(originalText); // Restore original text
        matchIndexes.clear();
        currentIndex = -1;
    }

    // Returns the number of matches found
    public int getMatchCount() {
        return matchIndexes.size();
    }
}
*/