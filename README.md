# Service features

## Skills
1. **[DONE]** Skill can be created via `POST /skill` endpoint. Validated for name presence and uniqueness
2. **[DONE]** Skill can be deleted via `DELETE /skill/{id}` endpoint
3. All user skills can be retrieved via `GET /skill/{userId}` endpoint
4. All skills offered to the user by other users can be retrieved via `GET /skill/{userId}/offered` endpoint. Importantly, the endpoint also returns the skills and the number of their offerings.
5. A user can get a skill from the offered skills via `POST /skill/{userId}/offered/{skillId}` endpoint. If the user already has the skill, it will not be added. If user does not have the skill, offers of that skill will be retrieved from the recommendations, calibrated for enough of them, and then the skill will be added to the user. Also, the authors of the recommendations will be added to this skill as guarantors of the skill.