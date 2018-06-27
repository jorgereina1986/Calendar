import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
export Events, { schema } from './model'

const router = new Router()
const { title, date, description, time } = schema.tree

/**
 * @api {post} /events Create events
 * @apiName CreateEvents
 * @apiGroup Events
 * @apiParam title Events's title.
 * @apiParam date Events's date.
 * @apiParam description Events's description.
 * @apiParam time Events's time.
 * @apiSuccess {Object} events Events's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Events not found.
 */
router.post('/',
  body({ title, date, description, time }),
  create)

/**
 * @api {get} /events Retrieve events
 * @apiName RetrieveEvents
 * @apiGroup Events
 * @apiUse listParams
 * @apiSuccess {Object[]} events List of events.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  query(),
  index)

/**
 * @api {get} /events/:id Retrieve events
 * @apiName RetrieveEvents
 * @apiGroup Events
 * @apiSuccess {Object} events Events's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Events not found.
 */
router.get('/:id',
  show)

/**
 * @api {put} /events/:id Update events
 * @apiName UpdateEvents
 * @apiGroup Events
 * @apiParam title Events's title.
 * @apiParam date Events's date.
 * @apiParam description Events's description.
 * @apiParam time Events's time.
 * @apiSuccess {Object} events Events's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Events not found.
 */
router.put('/:id',
  body({ title, date, description, time }),
  update)

/**
 * @api {delete} /events/:id Delete events
 * @apiName DeleteEvents
 * @apiGroup Events
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Events not found.
 */
router.delete('/:id',
  destroy)

export default router
