package org.entitypedia.games.quanda.client;

import com.fasterxml.jackson.core.type.TypeReference;
import org.entitypedia.games.common.client.GamesCommonClient;
import org.entitypedia.games.quanda.common.api.IQuandaAPI;
import org.entitypedia.games.quanda.common.model.Question;

/**
 * @author <a href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public class QuandaClient extends GamesCommonClient implements IQuandaClient {

    private static final TypeReference<Question> QUESTION_TYPE_REFERENCE = new TypeReference<Question>() {
    };

    private static final String DEFAULT_API_ENDPOINT = "http://games.entitypedia.org/quanda/webapi/";
    private static final String SECURE_API_ENDPOINT = "https://games.entitypedia.org/quanda/webapi/";

    public QuandaClient(String uid, String password) {
        super(DEFAULT_API_ENDPOINT, uid, password);
    }

    public QuandaClient(String uid, String password, Boolean secure) {
        super(SECURE_API_ENDPOINT, uid, password);
    }

    public QuandaClient(String uid, String password, String token, String tokenSecret) {
        super(DEFAULT_API_ENDPOINT, uid, password, token, tokenSecret);
    }

    public QuandaClient(String uid, String password, String token, String tokenSecret, Boolean secure) {
        super(SECURE_API_ENDPOINT, uid, password, token, tokenSecret);
    }

    @Override
    public Question getQuestion(Boolean changeTopic) {
        if (null != changeTopic) {
            return doSimpleGet(apiEndpoint + IQuandaAPI.GET_QUESTION + "?changeTopic=" + Boolean.toString(changeTopic), QUESTION_TYPE_REFERENCE);
        } else {
            return doSimpleGet(apiEndpoint + IQuandaAPI.GET_QUESTION, QUESTION_TYPE_REFERENCE);
        }
    }

    @Override
    public boolean postAnswer(long questionId, String answer) {
        return doPostRead(apiEndpoint + IQuandaAPI.POST_ANSWER +
                "?questionId=" + Long.toString(questionId) + "&answer=" + encodeURL(answer), BOOLEAN_TYPE_REFERENCE);
    }
}