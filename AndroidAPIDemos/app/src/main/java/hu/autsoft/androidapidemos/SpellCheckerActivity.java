package hu.autsoft.androidapidemos;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpellCheckerActivity extends AppCompatActivity implements SpellCheckerSession.SpellCheckerSessionListener {

    @BindView(R.id.etText)
    EditText etText;
    @BindView(R.id.tvResult)
    TextView tvResult;

    private SpellCheckerSession spellCheckerSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_checker);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final TextServicesManager tsm = (TextServicesManager) getSystemService(
                Context.TEXT_SERVICES_MANAGER_SERVICE);
        spellCheckerSession = tsm.newSpellCheckerSession(null, null, this, true);
    }

    @OnClick(R.id.btnCheckSpell)
    public void checkSpellClicked(View view) {
        if (!etText.getText().toString().isEmpty()) {
            tvResult.setText("");

            if (spellCheckerSession != null) {
                spellCheckerSession.getSentenceSuggestions(
                        new TextInfo[]{new TextInfo(etText.getText().toString())}, 1);
            } else {
                Log.e("TAG_SPELL", "Couldn't obtain the spell checker service.");
            }
        }
    }

    private void dumpSuggestionsInfoInternal(
            final StringBuilder sb, final SuggestionsInfo si, final int length, final int offset) {
        // Returned suggestions are contained in SuggestionsInfo
        final int len = si.getSuggestionsCount();
        sb.append('\n');
        for (int j = 0; j < len; ++j) {
            if (j != 0) {
                sb.append(", ");
            }
            sb.append(si.getSuggestionAt(j));
        }
        sb.append(" (" + len + ")");
        if (length != -1) {
            sb.append(" length = " + length + ", offset = " + offset);
        }
    }

    @Override
    public void onGetSuggestions(SuggestionsInfo[] suggestionsInfos) {
        // deprecated from JB
    }

    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfos) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentenceSuggestionsInfos.length; ++i) {
            final SentenceSuggestionsInfo ssi = sentenceSuggestionsInfos[i];
            for (int j = 0; j < ssi.getSuggestionsCount(); ++j) {
                dumpSuggestionsInfoInternal(
                        sb, ssi.getSuggestionsInfoAt(j), ssi.getOffsetAt(j), ssi.getLengthAt(j));
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResult.append(sb.toString());
            }
        });
    }
}
