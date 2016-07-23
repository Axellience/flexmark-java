package com.vladsch.flexmark.test;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.internal.util.KeepType;
import com.vladsch.flexmark.internal.util.options.DataHolder;
import com.vladsch.flexmark.internal.util.options.MutableDataSet;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.spec.SpecReader;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboExtraSpecTest extends ComboSpecTestCase {
    private static final String SPEC_RESOURCE = "/extra_ast_spec.md";
    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            .set(HtmlRenderer.PERCENT_ENCODE_URLS, true);

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("keep-last", new MutableDataSet().set(Parser.REFERENCES_KEEP, KeepType.LAST));
        optionsMap.put("hdr-no-atx-space", new MutableDataSet().set(Parser.HEADERS_NO_ATX_SPACE, true));
        optionsMap.put("hdr-no-lead-space", new MutableDataSet().set(Parser.HEADERS_NO_LEAD_SPACE, true));
        optionsMap.put("list-fixed-indent", new MutableDataSet().set(Parser.LISTS_FIXED_INDENT, 4));
        optionsMap.put("list-no-break", new MutableDataSet().set(Parser.LISTS_END_ON_DOUBLE_BLANK, false));
        optionsMap.put("list-break", new MutableDataSet().set(Parser.LISTS_END_ON_DOUBLE_BLANK, true));
        optionsMap.put("list-no-loose", new MutableDataSet().set(Parser.LISTS_AUTO_LOOSE, false));
        optionsMap.put("list-no-start", new MutableDataSet().set(Parser.ORDERED_LIST_START, false));
        optionsMap.put("list-no-bullet-match", new MutableDataSet().set(Parser.LISTS_BULLET_MATCH, false));
        optionsMap.put("list-no-relaxed-start", new MutableDataSet().set(Parser.LIST_INTERRUPTS_PARAGRAPH, false));
        optionsMap.put("ordered-no-relaxed-start", new MutableDataSet().set(Parser.ORDERED_LIST_INTERRUPTS_PARAGRAPH, false));
        optionsMap.put("ordered-breaks-parent", new MutableDataSet().set(Parser.ORDERED_SUBITEM_INTERRUPTS_PARENT_ITEM, true));
        optionsMap.put("thematic-break-no-relaxed-start", new MutableDataSet().set(Parser.THEMATIC_BREAK_RELAXED_START, false));
        optionsMap.put("test-completions", new MutableDataSet().set(Parser.THEMATIC_BREAK_RELAXED_START, false).set(HtmlRenderer.SUPPRESS_INLINE_HTML, true));
        optionsMap.put("escape-html", new MutableDataSet().set(HtmlRenderer.ESCAPE_HTML, true));
        optionsMap.put("escape-html-blocks", new MutableDataSet().set(HtmlRenderer.ESCAPE_HTML_BLOCKS, true));
        optionsMap.put("escape-html-comment-blocks", new MutableDataSet().set(HtmlRenderer.ESCAPE_HTML_COMMENT_BLOCKS, true));
        optionsMap.put("escape-inline-html", new MutableDataSet().set(HtmlRenderer.ESCAPE_INLINE_HTML, true));
        optionsMap.put("escape-inline-html-comments", new MutableDataSet().set(HtmlRenderer.ESCAPE_INLINE_HTML_COMMENTS, true));
        optionsMap.put("suppress-html", new MutableDataSet().set(HtmlRenderer.SUPPRESS_HTML, true));
        optionsMap.put("suppress-html-blocks", new MutableDataSet().set(HtmlRenderer.SUPPRESS_HTML_BLOCKS, true));
        optionsMap.put("suppress-html-comment-blocks", new MutableDataSet().set(HtmlRenderer.SUPPRESS_HTML_COMMENT_BLOCKS, true));
        optionsMap.put("suppress-inline-html", new MutableDataSet().set(HtmlRenderer.SUPPRESS_INLINE_HTML, true));
        optionsMap.put("suppress-inline-html-comments", new MutableDataSet().set(HtmlRenderer.SUPPRESS_INLINE_HTML_COMMENTS, true));
        optionsMap.put("no-class-prefix", new MutableDataSet().set(HtmlRenderer.LANGUAGE_CLASS_PREFIX, ""));
        //optionsMap.put("parse-anchor-links", new MutableDataSet().set(Parser.PARSE_INLINE_ANCHOR_LINKS, true));
        optionsMap.put("parse-inner-comments", new MutableDataSet().set(Parser.PARSE_INNER_HTML_COMMENTS, true));
        optionsMap.put("unmatched-fence", new MutableDataSet().set(Parser.MATCH_CLOSING_FENCE_CHARACTERS, false));
        optionsMap.put("dummy-identifier", new MutableDataSet().set(Parser.INTELLIJ_DUMMY_IDENTIFIER, true));
        optionsMap.put("code-trim-trailing", new MutableDataSet().set(Parser.INDENTED_CODE_NO_TRAILING_BLANK_LINES, true));
        optionsMap.put("ordered-dot-only", new MutableDataSet().set(Parser.ORDERED_LIST_DOT_ONLY, true));
    }

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    private static DataHolder optionsSet(String optionSet) {
        return optionsMap.get(optionSet);
    }

    public ComboExtraSpecTest(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        List<SpecExample> examples = SpecReader.readExamples(SPEC_RESOURCE);
        List<Object[]> data = new ArrayList<>();

        // NULL example runs full spec test
        data.add(new Object[] { SpecExample.NULL });

        for (SpecExample example : examples) {
            data.add(new Object[] { example });
        }
        return data;
    }

    @Override
    protected DataHolder options(String optionSet) {
        return optionsSet(optionSet);
    }

    @Override
    protected String getSpecResourceName() {
        return SPEC_RESOURCE;
    }

    @Override
    protected Parser parser() {
        return PARSER;
    }

    @Override
    protected HtmlRenderer renderer() {
        return RENDERER;
    }
}