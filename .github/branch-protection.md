# Branch protection

Pour bloquer le merge si la CI échoue sur GitHub, configurez la branche principale avec :

- Require a pull request before merging
- Require status checks to pass before merging
- Required status check : `test`

Le statut `test` est publié par le workflow GitHub Actions défini dans `.github/workflows/ci.yml`.