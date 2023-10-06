// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package generative_ai.actions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class JA_GenerateAccessToken extends CustomJavaAction<java.lang.String>
{
	private IMendixObject __CredentialFile;
	private generative_ai.proxies.Credential CredentialFile;

	public JA_GenerateAccessToken(IContext context, IMendixObject CredentialFile)
	{
		super(context);
		this.__CredentialFile = CredentialFile;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		this.CredentialFile = this.__CredentialFile == null ? null : generative_ai.proxies.Credential.initialize(getContext(), __CredentialFile);

		// BEGIN USER CODE
		IContext ctx = getContext();

			InputStream credentialFileInputStream = null;
			GoogleCredential credential = null;
			String accessToken = null;

			try {
				credentialFileInputStream = Core.getFileDocumentContent(ctx, __CredentialFile);
	
				if (credentialFileInputStream != null) {
					credential = GoogleCredential.fromStream(credentialFileInputStream)
							.createScoped(Collections.singleton("https://www.googleapis.com/auth/cloud-platform"));
	
					credential.refreshToken();
					accessToken = credential.getAccessToken();
				} else {
					throw new Exception("Credentials file input stream is null.");
				}
			} catch (IOException e) {
				throw new Exception("Error reading credentials file: " + e.getMessage(), e);
			} finally {
				if (credentialFileInputStream != null) {
					try {
						credentialFileInputStream.close();
					} catch (IOException e) {
					}
				}
			}
			return accessToken;

		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "JA_GenerateAccessToken";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
