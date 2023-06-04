
### Sign commit
1. install GPG
2. ```
   git config commit.gpgsign true
   ```
3.  
   ```
   gpg --full-generate-key
   ```
   - RSA and RSA
   - 4096
   - 1 year (recommended)
   - name
   - email as in GitHub
4. ```
   gpg --list-secret-keys --keyid-format LONG
   ```
5. ```
   gpg --armor --export 3AA5C34371567BD2
   ```
   ![img.png](img.png)
6. Copy and add in GitHub SSH and GPG keys
   ![img_1.png](img_1.png)
7. ```
   git config user.name "name"
   ```
8. ```
   git config user.email "email"
   ```
9. ```
   git config user.signingkey "signingkey form 4 step"
   ```

### !!!!Attention: 
If didn't work -> run in terminal: gpgconf --kill gpg-agent
