/* LanguageTool, a natural language style checker
 * Copyright (C) 2019 Sohaib Afifi, Taha Zerrouki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.ar;

import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.Language;
import org.languagetool.rules.Example;
import org.languagetool.rules.WordRepeatRule;

import java.util.ResourceBundle;

/**
 * Word repeat rule for َArabic, to avoid false alarms in the generic word repetition rule.
 */
public class ArabicWordRepeatRule extends WordRepeatRule {

  public ArabicWordRepeatRule(ResourceBundle messages, Language language) {
    super(messages, language);
    addExamplePair(Example.wrong("هذا <marker>فقط فقط</marker> مثال."),
                   Example.fixed("هذا <marker>فقط</marker> مثال."));
  }

  @Override
  public String getId() {
    return "ARABIC_WORD_REPEAT_RULE";
  }

  @Override
  public boolean ignore(AnalyzedTokenReadings[] tokens, int position) {
    if (wordRepetitionOf("خطوة", tokens, position)) {
      return true;   // "نفذت التعليمات خطوة خطوة."
    }
    if (wordRepetitionOf("رويدا", tokens, position)) {
      return true;
    }
    return false;
  }

  private boolean wordRepetitionOf(String word, AnalyzedTokenReadings[] tokens, int position) {
    return position > 0 && tokens[position - 1].getToken().equals(word) && tokens[position].getToken().equals(word);
  }

}
