package AAA.com.SW.EncryptorAndDecryptor;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;
import org.xml.sax.InputSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.util.DisplayMetrics;

public class XmlToSketchwareConverter {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    
    private static Context _CONTEXT; // Store context for density conversion

    public static void setContext(Context ctx) {
        _CONTEXT = ctx;
    }
    
    /*
    public static String convertXmlToSketchware(String xml, String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            document.getDocumentElement().normalize();

            List<Map<String, Object>> sketchwareViews = new ArrayList<>();
            convertElementToView(document.getDocumentElement(), "root", sketchwareViews, 0);

            StringBuilder output = new StringBuilder();

            // Add file name as the first line
            if (fileName == null || fileName.trim().isEmpty()) {
                fileName = "File_" + System.currentTimeMillis();
            }
            output.append("@").append(fileName).append(".xml").append("\n");

            // Convert views to Sketchware format (one line per view)
            for (Map<String, Object> view : sketchwareViews) {
                output.append(gson.toJson(view)).append("\n");
            }

            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
    */
    public static String convertXmlToSketchware(String xml, String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            document.getDocumentElement().normalize();
    
            List<Map<String, Object>> sketchwareViews = new ArrayList<>();
            convertElementToView(document.getDocumentElement(), "root", sketchwareViews, 0);
    
            StringBuilder output = new StringBuilder();
    
            // Add file name as the first line
            if (fileName == null || fileName.trim().isEmpty()) {
                fileName = "File_" + System.currentTimeMillis();
            }
            output.append("@").append(fileName).append(".xml").append("\n");
    
            // Store _fab if found
            Map<String, Object> _fabMap = null;
    
            // Convert views to Sketchware format (one line per view)
            for (Map<String, Object> view : sketchwareViews) {
                if (view.get("convert").equals("FloatingActionButton") && view.get("id").equals("_fab")) {
                    _fabMap = view; // Store _fab for later modification
                    continue; // Skip adding it now
                }
                output.append(gson.toJson(view)).append("\n");
            }
    
            // Always add the @fileName.xml_fab section
            output.append("@").append(fileName).append(".xml_fab").append("\n");
    
            // Modify @fileName.xml_fab if _fab exists, otherwise add default FAB
            if (_fabMap != null) {
                output.append(gson.toJson(_fabMap)).append("\n");
            } else {
                output.append(gson.toJson(getDefaultFabData())).append("\n");
            }
    
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private static void convertElementToView(Element element, String parentId, List<Map<String, Object>> sketchwareViews, int index) {
        LinkedHashMap<String, Object> viewData = getDefaultViewData();

        // Assign known attributes
        String tagName = element.getTagName();
        viewData.put("convert", tagName);
        viewData.put("id", getElementId(element, tagName));
        viewData.put("parent", parentId);
        viewData.put("index", index);
        viewData.put("layout", getLayoutProperties(element));

        // Extract text properties for text-based views
        if (tagName.equals("TextView") || tagName.equals("EditText")) {
            viewData.put("text", getTextProperties(element));
        }

        // Handle unknown attributes (store in `inject`)
        viewData.put("inject", getInjectProperties(element));

        sketchwareViews.add(viewData);

        // Process child elements
        NodeList childNodes = element.getChildNodes();
        int childIndex = 0;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                convertElementToView((Element) node, viewData.get("id").toString(), sketchwareViews, childIndex++);
            }
        }
    }

    private static LinkedHashMap<String, Object> getDefaultViewData() {
        LinkedHashMap<String, Object> viewData = new LinkedHashMap<>();

        viewData.put("adSize", "");
        viewData.put("adUnitId", "");
        viewData.put("alpha", 1.0);
        viewData.put("checked", 0);
        viewData.put("choiceMode", 0);
        viewData.put("clickable", 1);
        viewData.put("convert", "");
        viewData.put("customView", "");
        viewData.put("dividerHeight", 1);
        viewData.put("enabled", 1);
        viewData.put("firstDayOfWeek", 1);
        viewData.put("id", "");
        viewData.put("image", getImageDefaults());
        viewData.put("indeterminate", "false");
        viewData.put("index", 0);
        viewData.put("inject", "");
        viewData.put("layout", new LinkedHashMap<>());
        viewData.put("max", 100);
        viewData.put("parent", "root");
        viewData.put("parentType", 0);
        viewData.put("preId", "");
        viewData.put("preIndex", 0);
        viewData.put("preParentType", 0);
        viewData.put("progress", 0);
        viewData.put("progressStyle", "?android:progressBarStyle");
        viewData.put("scaleX", 1.0);
        viewData.put("scaleY", 1.0);
        viewData.put("spinnerMode", 1);
        viewData.put("text", getTextProperties(null));
        viewData.put("translationX", 0.0);
        viewData.put("translationY", 0.0);
        viewData.put("type", -1);

        return viewData;
    }

    private static String getElementId(Element element, String tagName) {
        String id = element.getAttribute("android:id");
        if (!id.isEmpty()) {
            return id.replace("@+id/", "");
        }
        return tagName + "_" + System.currentTimeMillis();
    }

    private static Map<String, Object> getLayoutProperties(Element element) {
        LinkedHashMap<String, Object> layout = new LinkedHashMap<>();

        layout.put("backgroundColor", -1);
        layout.put("borderColor", -16740915);
        layout.put("gravity", 0);
        layout.put("height", getLayoutValue(element.getAttribute("android:layout_height")));
        layout.put("layoutGravity", 0);
        layout.put("marginBottom", 4);
        layout.put("marginLeft", 0);
        layout.put("marginRight", 0);
        layout.put("marginTop", 4);
        layout.put("orientation", element.getTagName().equals("LinearLayout") ? 1 : -1);
        layout.put("paddingBottom", 4);
        layout.put("paddingLeft", 4);
        layout.put("paddingRight", 4);
        layout.put("paddingTop", 4);
        layout.put("weight", 0);
        layout.put("weightSum", 0);
        layout.put("width", getLayoutValue(element.getAttribute("android:layout_width")));

        return layout;
    }

    private static Map<String, Object> getTextProperties(Element element) {
        LinkedHashMap<String, Object> text = new LinkedHashMap<>();

        text.put("hint", element != null ? element.getAttribute("android:hint") : "");
        text.put("hintColor", -10453621);
        text.put("imeOption", 0);
        text.put("inputType", 1);
        text.put("line", 0);
        text.put("singleLine", 0);
        text.put("text", element != null ? element.getAttribute("android:text") : "");
        text.put("textColor", -16777216);
        text.put("textFont", "default_font");
        text.put("textSize", 12);
        text.put("textType", 0);

        return text;
    }

    private static String getInjectProperties(Element element) {
        StringBuilder inject = new StringBuilder();
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attr = attributes.item(i);
            String name = attr.getNodeName();
            if (!name.startsWith("android:layout_") && !name.equals("android:text") && !name.equals("android:id")) {
                inject.append(name).append("=\"").append(attr.getNodeValue()).append("\" ");
            }
        }
        return inject.toString().trim();
    }

    private static Map<String, Object> getImageDefaults() {
        LinkedHashMap<String, Object> image = new LinkedHashMap<>();
        image.put("resName", "default_image");
        image.put("rotate", 0);
        image.put("scaleType", "CENTER");
        return image;
    }
    
    
    private static int getLayoutValue(String value) {
        if (value == null || value.isEmpty()) return -2; // Default to wrap_content (-2)

        switch (value) {
            case "match_parent": return -1;
            case "wrap_content": return -2;
            default:
                try {
                    int num = Integer.parseInt(value.replaceAll("[^0-9]", "")); // Extract number
                    
                    if (value.contains("px")) {
                        return pxToDp(num);
                    } else {
                        return num; // Already in dp
                    }
                } catch (NumberFormatException e) {
                    return -2; // Default to wrap_content (-2) if error
                }
        }
    }

    private static int pxToDp(int px) {
        if (_CONTEXT == null) return px; // Prevent crash if context is missing
        DisplayMetrics metrics = _CONTEXT.getResources().getDisplayMetrics();
        return Math.round(px / (metrics.densityDpi / 160f));
    }
    
    
    
    private static LinkedHashMap<String, Object> getDefaultFabLayout() {
        LinkedHashMap<String, Object> layout = new LinkedHashMap<>();
        layout.put("backgroundColor", 16777215);
        layout.put("borderColor", -16740915);
        layout.put("gravity", 0);
        layout.put("height", -2);
        layout.put("layoutGravity", 85);
        layout.put("marginBottom", 16);
        layout.put("marginLeft", 16);
        layout.put("marginRight", 16);
        layout.put("marginTop", 16);
        layout.put("orientation", -1);
        layout.put("paddingBottom", 0);
        layout.put("paddingLeft", 0);
        layout.put("paddingRight", 0);
        layout.put("paddingTop", 0);
        layout.put("weight", 0);
        layout.put("weightSum", 0);
        layout.put("width", -2);
        return layout;
    }
    
    private static LinkedHashMap<String, Object> getDefaultFabImage() {
        LinkedHashMap<String, Object> image = new LinkedHashMap<>();
        image.put("resName", "NONE");
        image.put("rotate", 0);
        image.put("scaleType", "CENTER");
        return image;
    }
    
    private static LinkedHashMap<String, Object> getDefaultFabData() {
        LinkedHashMap<String, Object> fabData = new LinkedHashMap<>();
    
        fabData.put("adSize", "");
        fabData.put("adUnitId", "");
        fabData.put("alpha", 1.0);
        fabData.put("checked", 0);
        fabData.put("choiceMode", 0);
        fabData.put("clickable", 1);
        fabData.put("convert", "FloatingActionButton");
        fabData.put("customView", "");
        fabData.put("dividerHeight", 1);
        fabData.put("enabled", 1);
        fabData.put("firstDayOfWeek", 1);
        fabData.put("id", "_fab");
        fabData.put("image", getDefaultFabImage());
        fabData.put("indeterminate", "false");
        fabData.put("index", 0);
        fabData.put("inject", "");
        fabData.put("layout", getDefaultFabLayout());
        fabData.put("max", 100);
        fabData.put("parentType", -1);
        fabData.put("preIndex", 0);
        fabData.put("preParentType", 0);
        fabData.put("progress", 0);
        fabData.put("progressStyle", "?android:progressBarStyle");
        fabData.put("scaleX", 1.0);
        fabData.put("scaleY", 1.0);
        fabData.put("spinnerMode", 1);
        fabData.put("text", getTextProperties(null));
        fabData.put("translationX", 0.0);
        fabData.put("translationY", 0.0);
        fabData.put("type", 16);
    
        return fabData;
    }
    
    
}

